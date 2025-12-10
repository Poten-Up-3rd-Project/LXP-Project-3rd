package com.lxp.enrollment.infrastructure.persistence.adapter;

import com.lxp.enrollment.application.port.required.CancelEnrollmentPort;
import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.infrastructure.persistence.entity.EnrollmentJpaEntity;
import com.lxp.enrollment.infrastructure.persistence.repository.EnrollmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateStateEnrollmentAdapter implements CancelEnrollmentPort {
    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public Enrollment findById(long enrollmentId) {
        return enrollmentJpaRepository.findById(enrollmentId)
                .orElseThrow(() -> new IllegalStateException("todo"))
                .toDomain();
    }

    public void cancel(Enrollment enrollment) {
        EnrollmentJpaEntity entity = EnrollmentJpaEntity.from(enrollment);
        enrollmentJpaRepository.save(entity);
    }
}
