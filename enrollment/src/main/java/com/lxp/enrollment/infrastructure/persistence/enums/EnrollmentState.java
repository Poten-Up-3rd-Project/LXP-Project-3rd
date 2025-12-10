package com.lxp.enrollment.infrastructure.persistence.enums;

import java.util.Arrays;

public enum EnrollmentState {
    ENROLLED, COMPLETED, CANCELED;

    public static EnrollmentState from(String val) {
        return Arrays.stream(values())
                .filter(type -> type.name().equals(val))
                .findAny()
                .orElse(null);
    }
}
