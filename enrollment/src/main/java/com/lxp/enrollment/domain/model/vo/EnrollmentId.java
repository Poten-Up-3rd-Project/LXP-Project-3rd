package com.lxp.enrollment.domain.model.vo;

public record EnrollmentId(Long value) {

    public EnrollmentId {
        if (value != null && value <= 0) {
            throw new IllegalArgumentException("EnrollmentId must be positive when assigned");
        }
    }
}
