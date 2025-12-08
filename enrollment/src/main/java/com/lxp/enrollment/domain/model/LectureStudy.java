package com.lxp.enrollment.domain.model;

import com.lxp.enrollment.domain.model.vo.LectureId;
import com.lxp.enrollment.domain.model.vo.LectureStudyId;
import com.lxp.enrollment.domain.model.vo.UserId;

import java.util.Objects;

/**
 * 강의 진행률 도메인
 */
public class LectureStudy {

    private LectureStudyId lectureStudyId;
    private UserId userId;
    private LectureId lectureId;
    private boolean isCompleted;

    /**
     * 강의 진행률 생성
     * @param lectureStudyId 강의 진행률 ID(학습자 ID + 강의 ID)
     * @return 생성 된 강의 진행률
     */
    public static LectureStudy createLectureStudy(LectureStudyId lectureStudyId, UserId userId, LectureId lectureId) {
        return new LectureStudy(
                Objects.requireNonNull(lectureStudyId, "LectureStudyId는 null일 수 없습니다."),
                Objects.requireNonNull(userId, "UserId는 null일 수 없습니다."),
                Objects.requireNonNull(lectureId, "lectureId는 null일 수 없습니다."),
                false
        );
    }

    /**
     * 강의 기록 완료 상태로 변경
     */
    public void changeCompleted() {
        this.isCompleted = true;
    }

    /**
     * 강의 진행률 완료 여부
     * @return 진행 완료 여부
     */
    public boolean completed() {
        return this.isCompleted;
    }

    public LectureStudyId lectureStudyId() {
        return lectureStudyId;
    }

    private LectureStudy(LectureStudyId lectureStudyId, UserId userId, LectureId lectureId, boolean isCompleted) {
        this.lectureStudyId = lectureStudyId;
        this.userId = userId;
        this.lectureId = lectureId;
        this.isCompleted = isCompleted;
    }
}
