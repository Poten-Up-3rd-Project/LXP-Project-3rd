package com.lxp.enrollment.interfaces;

import com.lxp.api.enrollment.dto.query.GetEnrollmentHistoryQuery;
import com.lxp.common.annotation.CurrentUserId;
import com.lxp.common.domain.pagination.Page;
import com.lxp.enrollment.application.port.provided.dto.result.EnrollmentHistoryItem;
import com.lxp.enrollment.application.port.provided.usecase.GetEnrollmentListUseCase;
import com.lxp.enrollment.interfaces.dto.response.EnrollmentSummaryResponse;
import com.lxp.enrollment.interfaces.dto.response.GetEnrollmentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentQueryController {
    private final GetEnrollmentListUseCase getEnrollmentListUseCase;

    @GetMapping
    public ResponseEntity<GetEnrollmentListResponse> getEnrollments(
            @CurrentUserId String userId,
            @RequestParam(name = "state") String state,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size,
            @RequestParam(name = "sort") String sort
    ) {
        Page<EnrollmentHistoryItem> result = getEnrollmentListUseCase.getEnrollmentHistory(
            new GetEnrollmentHistoryQuery(userId, state, page, size, sort)
        );
        GetEnrollmentListResponse response = new GetEnrollmentListResponse(
                result.content().stream().map(EnrollmentSummaryResponse::from).toList(),
                result.totalElements(),
                result.totalPages(),
                result.pageSize()
        );
        return ResponseEntity.ok(response);
    }
}
