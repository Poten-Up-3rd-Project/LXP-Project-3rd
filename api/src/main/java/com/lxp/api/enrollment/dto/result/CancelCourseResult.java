package com.lxp.api.enrollment.dto.result;

import java.time.LocalDateTime;

public record CancelCourseResult(
    long enrollmentId,
    String state, // ENROLLED, COMPLETED, CANCELLED
    LocalDateTime enrolledAt
) {

}
