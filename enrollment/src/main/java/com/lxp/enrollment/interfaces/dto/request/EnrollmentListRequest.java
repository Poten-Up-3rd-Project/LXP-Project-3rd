package com.lxp.enrollment.interfaces.dto.request;

public record EnrollmentListRequest(
        String userId,
        String state,
        int page,
        int size,
        String sort
) {
}
