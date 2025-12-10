package com.lxp.recommend.application.service;

import com.lxp.recommend.application.dto.RecommendedCourseDto;
import com.lxp.recommend.application.port.required.CourseMetaReader;
import com.lxp.recommend.application.port.required.LearningStatusReader;
import com.lxp.recommend.application.port.required.MemberProfileReader;
import com.lxp.recommend.domain.dto.CourseMetaView;
import com.lxp.recommend.domain.dto.DifficultyLevel;
import com.lxp.recommend.domain.dto.LearnerLevel;
import com.lxp.recommend.domain.dto.LearnerProfileView;
import com.lxp.recommend.domain.dto.LearningStatusView;
import com.lxp.recommend.domain.model.*;
import com.lxp.recommend.domain.model.ids.CourseId;
import com.lxp.recommend.domain.model.ids.MemberId;
import com.lxp.recommend.domain.repository.MemberRecommendationRepository;
import com.lxp.recommend.domain.service.RecommendScoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 추천 애플리케이션 서비스
 *
 * 책임:
 * 1. 유스케이스 조율 (흐름 제어)
 * 2. Raw Data 조회 (Port 호출)
 * 3. 도메인 객체 조립 (RecommendContext)
 * 4. 도메인 서비스 호출
 * 5. Aggregate 저장
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendApplicationService {

    private static final int CANDIDATE_LIMIT = 100;

    private final MemberProfileReader memberProfileReader;
    private final CourseMetaReader courseMetaReader;
    private final LearningStatusReader learningStatusReader;
    private final MemberRecommendationRepository recommendationRepository;
    private final RecommendScoringService scoringService;

    /**
     * [Command] 추천 재계산 (비동기)
     *
     * @param rawMemberId 회원 ID (원시 타입)
     */
    @Async
    @Transactional
    public void refreshRecommendationAsync(String rawMemberId) {
        log.info("[추천 계산 시작] memberId={}", rawMemberId);

        try {
            executeRefresh(rawMemberId);
        } catch (Exception e) {
            // 비동기 메서드는 예외가 조용히 사라지므로 반드시 로깅
            log.error("[추천 계산 실패] memberId={}, error={}", rawMemberId, e.getMessage(), e);
        }
    }

    /**
     * [Command] 추천 재계산 (동기)
     *
     * @param rawMemberId 회원 ID (원시 타입)
     */
    @Transactional
    public void refreshRecommendation(String rawMemberId) {
        log.info("[추천 계산 시작 (동기)] memberId={}", rawMemberId);
        executeRefresh(rawMemberId);
    }

    /**
     * [Query] UI 노출용 추천 조회
     *
     * @param rawMemberId 회원 ID
     * @return 추천 강좌 DTO 리스트
     */
    @Transactional(readOnly = true)
    public List<RecommendedCourseDto> getTopRecommendations(String rawMemberId) {
        MemberId memberId = MemberId.of(rawMemberId);

        return recommendationRepository.findByMemberId(memberId)
                .map(MemberRecommendation::getItems)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ===== Private Methods: 핵심 로직 =====

    /**
     * 추천 재계산 실행 (공통 로직)
     */
    private void executeRefresh(String rawMemberId) {
        MemberId memberId = MemberId.of(rawMemberId);

        // 1. Raw Data 조회 (Application의 책임)
        LearnerProfileView profile = memberProfileReader.getProfile(rawMemberId);
        if (profile == null) {
            log.warn("[추천 계산 중단] 프로필 없음. memberId={}", rawMemberId);
            return;
        }

        Set<DifficultyLevel> targetDifficulties = determineTargetDifficulties(profile.learnerLevel());

        List<CourseMetaView> candidateViews = courseMetaReader.findByDifficulties(targetDifficulties, CANDIDATE_LIMIT);
        if (candidateViews.isEmpty()) {
            log.info("[추천 계산 중단] 후보 강좌 없음. difficulties={}", targetDifficulties);
            return;
        }

        List<LearningStatusView> learningHistories = learningStatusReader.findByMemberId(rawMemberId);

        // 2. DTO → 도메인 객체 변환
        List<CourseCandidate> candidates = candidateViews.stream()
                .map(this::toDomainCandidate)
                .toList();

        // 3. 도메인 컨텍스트 생성 (도메인의 책임: 조립 + 필터링 + 검증)
        RecommendContext context = RecommendContext.create(
                profile.selectedTags(),
                learningHistories,
                candidates
        );

        if (!context.hasValidContext()) {
            log.info("[추천 계산 중단] 유효한 컨텍스트 없음. memberId={}", rawMemberId);
            return;
        }

        // 4. 점수 계산 (도메인 서비스)
        List<RecommendedCourse> scoredCourses = scoringService.scoreAndRank(
                context,
                ScoringPolicy.defaultPolicy() // 정책 주입 (교체 가능)
        );

        if (scoredCourses.isEmpty()) {
            log.info("[추천 계산 중단] 점수 계산 결과 없음. memberId={}", rawMemberId);
            return;
        }

        // 5. Aggregate 저장 (도메인의 책임: 불변식 검증)
        MemberRecommendation recommendation = recommendationRepository
                .findByMemberId(memberId)
                .orElseGet(() -> new MemberRecommendation(memberId));

        recommendation.updateItems(scoredCourses); // 불변식 검증 포함
        recommendationRepository.save(recommendation);

        log.info("[추천 계산 완료] memberId={}, 추천 수={}", rawMemberId, scoredCourses.size());
    }

    // ===== Mapper: DTO → Domain =====

    /**
     * CourseMetaView (DTO) → CourseCandidate (도메인 객체)
     */
    private CourseCandidate toDomainCandidate(CourseMetaView view) {
        return new CourseCandidate(
                CourseId.of(view.courseId()),
                view.tags(),
                view.difficulty(),
                view.isPublic()
        );
    }

    /**
     * RecommendedCourse (도메인) → RecommendedCourseDto (DTO)
     */
    private RecommendedCourseDto toDto(RecommendedCourse course) {
        return new RecommendedCourseDto(
                course.getCourseId().getValue(),
                course.getScore(),
                course.getRank()
        );
    }

    // ===== 도메인 규칙: 난이도 결정 =====

    /**
     * 학습자 레벨에 따른 추천 대상 난이도 결정
     *
     * 규칙: 내 레벨 + 한 단계 위
     */
    private Set<DifficultyLevel> determineTargetDifficulties(LearnerLevel learnerLevel) {
        return switch (learnerLevel) {
            case JUNIOR -> Set.of(DifficultyLevel.JUNIOR, DifficultyLevel.MIDDLE);
            case MIDDLE -> Set.of(DifficultyLevel.MIDDLE, DifficultyLevel.SENIOR);
            case SENIOR -> Set.of(DifficultyLevel.SENIOR, DifficultyLevel.EXPERT);
            case EXPERT -> Set.of(DifficultyLevel.EXPERT);
        };
    }
}
