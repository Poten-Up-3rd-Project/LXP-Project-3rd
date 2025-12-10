package com.lxp.enrollment.infrastructure.persistence.adapter;

import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.infrastructure.persistence.entity.EnrollmentJpaEntity;
import com.lxp.enrollment.infrastructure.persistence.repository.EnrollmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentCommandAdapter {
    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public void updateState(Enrollment enrollment) {
    }
}
