package com.lxp.enrollment.application.port.provided.dto.enums;

import com.lxp.enrollment.domain.exception.EnrollmentErrorCode;
import com.lxp.enrollment.domain.exception.EnrollmentException;

import java.util.Locale;

public enum UserStatus {
    ACTIVE,
    DELETED;

    public static UserStatus from(String state) {
        try {
            return UserStatus.valueOf(state.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new EnrollmentException(EnrollmentErrorCode.INVALID_USER_STATUS);
        }
    }

    public boolean canEnroll() {
        return this == ACTIVE;
    }
}
