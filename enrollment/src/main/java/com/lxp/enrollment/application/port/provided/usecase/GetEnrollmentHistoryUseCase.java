package com.lxp.enrollment.application.port.provided.usecase;

import com.lxp.api.enrollment.dto.query.GetEnrollmentHistoryQuery;
import com.lxp.api.enrollment.dto.result.EnrollmentHistoryItem;
import com.lxp.common.domain.pagination.Page;

public interface GetEnrollmentHistoryUseCase {
    Page<EnrollmentHistoryItem> getEnrollmentHistory(GetEnrollmentHistoryQuery query);
}
