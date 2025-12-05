package com.lxp.enrollment.domain.model.vo;

public record LectureStudyId(String value) {
    public LectureStudyId {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("LectureStudyId cannot be null or blank");
        }
    }
}
