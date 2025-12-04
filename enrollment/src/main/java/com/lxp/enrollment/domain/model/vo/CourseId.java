package com.lxp.enrollment.domain.model.vo;

public record CourseId(String value) {
    public CourseId {
        if (value != null && value.isEmpty()) {
            throw new IllegalArgumentException("UserId must be not empty");
        }
    }
}
