package com.lxp.enrollment.application.port.provided.dto;

public record EnrollCourseResult(
    long enrollmentId,
    String state // ENROLLED, COMPLETED, CANCELLED
) {

}
