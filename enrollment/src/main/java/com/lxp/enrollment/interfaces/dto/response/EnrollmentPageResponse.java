package com.lxp.enrollment.interfaces.dto.response;

import java.util.List;

public record EnrollmentPageResponse(
        List<EnrollmentResponse> content,
        int totalElements,
        int totalPages,
        int page,
        int size
) {
}
