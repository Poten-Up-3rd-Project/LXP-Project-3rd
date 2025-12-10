package com.lxp.enrollment.application.port.provided.dto.result;

public record CancelCourseResult(
    long enrollmentId,
    String state // ENROLLED, COMPLETED, CANCELLED
) {

}
