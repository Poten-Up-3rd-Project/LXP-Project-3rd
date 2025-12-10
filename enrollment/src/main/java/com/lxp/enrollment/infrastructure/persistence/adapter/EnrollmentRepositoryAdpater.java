package com.lxp.enrollment.infrastructure.persistence.adapter;

import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.domain.repository.EnrollmentRepository;

import java.util.Optional;

public class EnrollmentRepositoryAdpater implements EnrollmentRepository {

    @Override
    public Optional<Enrollment> findById(Long id) {
        return Optional.empty();
    }
}
