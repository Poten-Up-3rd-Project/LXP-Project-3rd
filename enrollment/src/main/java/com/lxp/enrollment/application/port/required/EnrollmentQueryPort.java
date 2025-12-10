package com.lxp.enrollment.application.port.required;

import com.lxp.common.domain.pagination.Page;
import com.lxp.enrollment.application.port.query.EnrollmentResult;

public interface EnrollmentQueryPort {
    Page<EnrollmentResult> findEnrollments(String userId, String state, int page, int size);
}
