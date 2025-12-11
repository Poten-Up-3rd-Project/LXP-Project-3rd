package com.lxp.enrollment.domain.repository;

import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.infrastructure.persistence.entity.EnrollmentJpaEntity;

import java.util.Optional;

public interface EnrollmentRepository {
    Optional<Enrollment> findById(Long id);
}
