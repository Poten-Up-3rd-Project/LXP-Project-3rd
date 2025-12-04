package com.lxp.enrollment.domain.model.vo;

import java.time.LocalDateTime;
import java.util.Objects;

public record EnrollmentDate(LocalDateTime value) {
    private static final long CANCEL_LIMIT_DAYS = 3L;

    public EnrollmentDate {
        Objects.requireNonNull(value, "value is must not be null");
    }

    public boolean canCancel(LocalDateTime now) {
        Objects.requireNonNull(now, "now is must not be null");
        LocalDateTime deadline = value.plusDays(CANCEL_LIMIT_DAYS);
        return !now.isAfter(deadline);
    }
}
