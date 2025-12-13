package com.lxp.enrollment.domain.repository;

import com.lxp.common.domain.pagination.Page;
import com.lxp.common.domain.pagination.PageRequest;
import com.lxp.enrollment.domain.model.Enrollment;

public interface EnrollmentRepository {
    Enrollment findById(long enrollmentId);
    Enrollment save(Enrollment enrollment);
    Page<Enrollment> findByUserIdAndState(String userId, String state, PageRequest pageRequest);
}