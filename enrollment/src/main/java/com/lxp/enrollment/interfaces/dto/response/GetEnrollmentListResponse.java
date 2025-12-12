package com.lxp.enrollment.interfaces.dto.response;

import java.util.List;

public record GetEnrollmentListResponse(
        List<EnrollmentSummaryResponse> content,
        int totalElements,
        int totalPage,
        int size
) {
}
