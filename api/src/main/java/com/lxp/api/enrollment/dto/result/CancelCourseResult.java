package com.lxp.api.enrollment.dto.result;

public record CancelCourseResult(
    long enrollmentId,
    String state // ENROLLED, COMPLETED, CANCELLED
) {

}
