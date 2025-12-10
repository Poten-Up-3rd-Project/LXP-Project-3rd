package com.lxp.enrollment.application.port.provided.dto;

import java.time.LocalDateTime;

public record CancelCourseResult(
    long enrollmentId,
    String state, // ENROLLED, COMPLETED, CANCELLED
    LocalDateTime enrolledAt
) {

}
