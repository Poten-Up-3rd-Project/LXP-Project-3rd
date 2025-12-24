package com.lxp.recommend.application.service;

import com.lxp.recommend.application.dto.CourseMetaData;
import com.lxp.recommend.application.dto.LearnerProfileData;
import com.lxp.recommend.application.dto.LearningHistoryData;
import com.lxp.recommend.application.port.required.CourseMetaQueryPort;
import com.lxp.recommend.application.port.required.LearnerProfileQueryPort;
import com.lxp.recommend.application.port.required.LearningHistoryQueryPort;

import com.lxp.recommend.application.port.provided.persistence.MemberRecommendationRepository;
import com.lxp.recommend.domain.model.*;
import com.lxp.recommend.domain.model.ids.CourseId;
import com.lxp.recommend.domain.model.ids.EnrollmentStatus;
import com.lxp.recommend.domain.model.ids.MemberId;
import com.lxp.recommend.domain.policy.ScoringPolicy;  // 가정: ScoringPolicy 로직 내장된 경우
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecommendCommandService {

    // ✅ Port 직접 주입 (Assembler 제거)
    private final MemberRecommendationRepository recommendationRepository;
    private final LearnerProfileQueryPort userPort;
    private final CourseMetaQueryPort coursePort;
    private final LearningHistoryQueryPort historyPort;

    // ✅ ScoringService 제거 -> Policy 직접 사용 (또는 Service 내 private method)
    // private final RecommendScoringService scoringService; (제거)

    @Transactional
    public void refreshRecommendation(String learnerId) {
        log.info("[추천 계산 시작] learnerId={}", learnerId);

        // 1. 외부 데이터 수집 (Assembler 로직 흡수)
        RecommendContext context = assembleContext(learnerId);

        if (!context.hasValidContext()) {
            log.info("[추천 계산 중단] 유효한 컨텍스트 없음.");
            return;
        }

        // 2. 도메인 로직 (점수 계산)
        // ScoringPolicy가 도메인 모델 내에서 동작하도록 변경 권장
        List<RecommendedCourse> scoredCourses = calculateScores(context);

        if (scoredCourses.isEmpty()) {
            return;
        }

        // 3. 저장
        MemberRecommendation recommendation = findOrCreateRecommendation(MemberId.of(learnerId));
        recommendation.updateItems(scoredCourses);
        recommendationRepository.save(recommendation);

        log.info("[추천 계산 완료]");
    }

    // 🔴 과거 Assembler의 로직을 여기로 가져옴
    private RecommendContext assembleContext(String learnerId) {
        // 1. 프로필 조회
        LearnerProfileData profile = userPort.getProfile(learnerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 2. 학습 이력 조회
        List<LearningHistoryData> historyDtos = historyPort.findByLearnerId(learnerId);
        List<LearningHistory> histories = historyDtos.stream()
                .map(d -> new LearningHistory(CourseId.of(d.courseId()), EnrollmentStatus.valueOf(d.status())))
                .toList();

        // 3. 후보 강좌 조회 (난이도 기반)
        // LevelMapper 로직 활용 필요 (infrastructure.external.common.LevelMapper -> static method)
        // Set<String> targetLevels = LevelMapper.toStringSet(LevelMapper.determineTargetLevels(Level.valueOf(profile.learnerLevel())));
        Set<String> targetLevels = Set.of("JUNIOR", "MIDDLE"); // 임시 (LevelMapper 가져와야 함)

        List<CourseMetaData> courseDtos = coursePort.findByDifficulties(targetLevels, 50);
        List<CourseCandidate> candidates = courseDtos.stream()
                .map(d -> new CourseCandidate(
                        CourseId.of(d.courseId()),
                        d.tags(),
                        com.lxp.common.enums.Level.valueOf(d.difficulty()),
                        d.isPublic()
                ))
                .toList();

        return RecommendContext.create(profile.interestTags(), histories, candidates);
    }

    // 🔴 과거 ScoringService의 로직 흡수
    private List<RecommendedCourse> calculateScores(RecommendContext context) {
        ScoringPolicy policy = ScoringPolicy.defaultPolicy();

        return context.getFilteredCandidates().stream()
                .map(candidate -> {
                    double score = policy.calculateScore(candidate.getTags(), context.getTagContext());
                    return new RecommendedCourse(candidate.getCourseId(), score);
                })
                .filter(rc -> rc.getScore() > 0)
                .sorted((c1, c2) -> Double.compare(c2.getScore(), c1.getScore())) // 내림차순
                .limit(10)
                .toList();
    }

    private MemberRecommendation findOrCreateRecommendation(MemberId memberId) {
        return recommendationRepository.findByMemberId(memberId)
                .orElseGet(() -> new MemberRecommendation(memberId));
    }
}
