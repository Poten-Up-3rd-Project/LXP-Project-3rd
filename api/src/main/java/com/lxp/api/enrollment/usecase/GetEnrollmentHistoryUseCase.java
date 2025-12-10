package com.lxp.api.enrollment.usecase;

import com.lxp.api.enrollment.dto.query.GetEnrollmentHistoryQuery;
import com.lxp.api.enrollment.dto.result.EnrollmentHistoryItem;
import com.lxp.common.domain.pagination.Page;

public interface GetEnrollmentHistoryUseCase {
    Page<EnrollmentHistoryItem> getEnrollmentHistory(GetEnrollmentHistoryQuery query);
}
