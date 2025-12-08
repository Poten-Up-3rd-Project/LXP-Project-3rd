package com.lxp.enrollment.domain.model.vo;

import java.util.Objects;

public record LectureStudyId(Long value) {
    public LectureStudyId {
        if(value == null) {
            throw new IllegalArgumentException("LectureStudyId cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureStudyId that = (LectureStudyId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
