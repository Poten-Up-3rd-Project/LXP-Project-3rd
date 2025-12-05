package com.lxp.enrollment.domain.model;

import com.lxp.enrollment.domain.model.enums.StudyStatus;
import com.lxp.enrollment.domain.model.vo.CourseStudyId;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * 강좌 진행률 도메인
 */
public class CourseStudy {

    private CourseStudyId courseStudyId;
    private float totalProgress;
    private StudyStatus studyStatus;
    private OffsetDateTime completedAt;

    private final List<LectureStudy> lectureStudies;

    /**
     * 강좌 진행률 생성
     * @param courseStudyId 강좌 진행률 ID(학습자ID + '_' + 강좌 ID)
     * @param lectureStudies 강의 진행률 리스트
     * @return 생성된 강좌 진행률
     */
    public static CourseStudy createCourseStudy(CourseStudyId courseStudyId, List<LectureStudy> lectureStudies) {
        return new CourseStudy.Builder()
                .courseStudyId(courseStudyId)
                .totalProgress(0.0f)
                .studyStatus(StudyStatus.IN_PROGRESS)
                .completedAt(null)
                .lectureStudies(lectureStudies)
                .build();
    }

    /**
     * 강좌 진행률의 진행률 재 계산
     * 소수점 버림
     */
    public void recalculateProgress() {
        if(this.studyStatus == StudyStatus.COMPLETED) {
            throw new IllegalStateException("완료 상태의 강의는 진도를 업데이트 할 수 없습니다.");
        }

        float total = ((float) lectureStudies.stream().filter(LectureStudy::isCompleted).count()) /
                lectureStudies.size() * 100;

        this.totalProgress = BigDecimal.valueOf(total).setScale(0, RoundingMode.FLOOR).floatValue();

        determineCompletion();
    }

    /**
     * 강좌 완료 처리
     */
    private void determineCompletion() {
        if (this.totalProgress == 100) {
            this.studyStatus = StudyStatus.COMPLETED;
            this.completedAt = OffsetDateTime.now();
        }
    }

    /*
     * Getters
     */
    public CourseStudyId getCourseStudyId() {
        return courseStudyId;
    }

    public float getTotalProgress() {
        return totalProgress;
    }

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public OffsetDateTime getCompletedAt() {
        return completedAt;
    }

    public List<LectureStudy> getLectureStudies() {
        return lectureStudies;
    }

    /*
     * Builder pattern
     */
    private CourseStudy(Builder builder) {
        this.courseStudyId = builder.courseStudyId;
        this.totalProgress = builder.totalProgress;
        this.studyStatus = builder.studyStatus;
        this.completedAt = builder.completedAt;
        this.lectureStudies = builder.lectureStudies;
    }

    public static class Builder {
        private CourseStudyId courseStudyId;
        private float totalProgress;
        private StudyStatus studyStatus;
        private OffsetDateTime completedAt;
        private List<LectureStudy> lectureStudies;

        public Builder courseStudyId(CourseStudyId courseStudyId) {
            this.courseStudyId = courseStudyId;
            return this;
        }

        public Builder totalProgress(float totalProgress) {
            this.totalProgress = totalProgress;
            return this;
        }

        public Builder studyStatus(StudyStatus studyStatus) {
            this.studyStatus = studyStatus;
            return this;
        }

        public Builder completedAt(OffsetDateTime completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        public Builder lectureStudies(List<LectureStudy> lectureStudies) {
            this.lectureStudies = lectureStudies;
            return this;
        }

        public CourseStudy build() {
            return new CourseStudy(this);
        }
    }

}
