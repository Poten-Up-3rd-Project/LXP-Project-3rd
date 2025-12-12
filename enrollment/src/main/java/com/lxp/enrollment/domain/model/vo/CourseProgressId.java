package com.lxp.enrollment.domain.model.vo;

import java.util.Objects;

public record CourseProgressId(Long value) {
    public CourseProgressId {
        if(value == null) {
            throw new IllegalArgumentException("CourseStudyId cannot be null");
        }
    }

}
