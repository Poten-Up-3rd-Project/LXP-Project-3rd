package com.lxp.enrollment.infrastructure.persistence.adapter;

import com.lxp.enrollment.application.port.required.CancelEnrollmentPort;
import com.lxp.enrollment.domain.exception.EnrollmentErrorCode;
import com.lxp.enrollment.domain.exception.EnrollmentException;
import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.infrastructure.persistence.entity.EnrollmentJpaEntity;
import com.lxp.enrollment.infrastructure.persistence.repository.EnrollmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelEnrollmentAdapter implements CancelEnrollmentPort {
    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public Enrollment findById(long enrollmentId) {
        return enrollmentJpaRepository.findById(enrollmentId)
                .orElseThrow(() -> new EnrollmentException(EnrollmentErrorCode.NOT_FOUND_ENROLLMENT))
                .toDomain();
    }

    public void cancel(Enrollment enrollment) {
        EnrollmentJpaEntity entity = EnrollmentJpaEntity.from(enrollment);
        enrollmentJpaRepository.save(entity);
    }
}
