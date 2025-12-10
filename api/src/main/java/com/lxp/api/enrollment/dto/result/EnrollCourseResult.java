package com.lxp.api.enrollment.dto.result;

public record EnrollCourseResult(
    long enrollmentId,
    String state // ENROLLED, COMPLETED, CANCELLED
) {

}
