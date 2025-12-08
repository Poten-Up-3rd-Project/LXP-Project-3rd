package com.lxp.enrollment.domain.model.vo;

import java.util.Objects;

public record CourseStudyId(Long value) {
    public CourseStudyId {
        if(value == null) {
            throw new IllegalArgumentException("CourseStudyId cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudyId that = (CourseStudyId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
