package com.lxp.enrollment.interfaces.dto.request;

public record GetEnrollmentRequest(
        String userId,
        String state, // ENROLLED, COMPLETED, CANCELLED
        int page,
        int size,
        String sort // LATEST, OLDEST
) {
}
