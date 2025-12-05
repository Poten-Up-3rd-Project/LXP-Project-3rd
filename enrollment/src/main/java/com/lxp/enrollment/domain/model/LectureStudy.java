package com.lxp.enrollment.domain.model;

import com.lxp.enrollment.domain.model.vo.LectureStudyId;

/**
 * 강의 진행률 도메인
 */
public class LectureStudy {

    private LectureStudyId lectureStudyId;
    private boolean isCompleted;

    /**
     * 강의 진행률 생성
     * @param lectureStudyId 강의 진행률 ID(학습자 ID + 강의 ID)
     * @return 생성 된 강의 진행률
     */
    public static LectureStudy createLectureStudy(LectureStudyId lectureStudyId) {
        return new Builder()
                .lectureStudyId(lectureStudyId)
                .isCompleted(false)
                .build();
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
    public boolean isCompleted() {
        return this.isCompleted;
    }

    /*
     * Builder Pattern
     */
    private LectureStudy(Builder builder) {
        this.lectureStudyId = builder.lectureStudyId;
        this.isCompleted = builder.isCompleted;
    }

    public static class Builder {
        private LectureStudyId lectureStudyId;
        private boolean isCompleted;

        public Builder lectureStudyId(LectureStudyId lectureStudyId) {
            this.lectureStudyId = lectureStudyId;
            return this;
        }

        public Builder isCompleted(boolean isCompleted) {
            this.isCompleted = isCompleted;
            return this;
        }

        public LectureStudy build() {
            return new LectureStudy(this);
        }


    }

}
