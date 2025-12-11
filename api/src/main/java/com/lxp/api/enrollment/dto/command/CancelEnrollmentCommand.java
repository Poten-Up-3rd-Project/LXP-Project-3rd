package com.lxp.api.enrollment.dto.command;

public record CancelEnrollmentCommand(
    long enrollmentId,
    String reason
) {

}
