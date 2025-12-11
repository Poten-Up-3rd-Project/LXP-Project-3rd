package com.lxp.enrollment.application.port.query;

public record EnrollmentResult(
        long id,
        String state, // ENROLLED, COMPLETED, CANCELED
        String userUUID,
        String courseUUID
) {
}
