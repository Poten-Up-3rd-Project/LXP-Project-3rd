package com.lxp.enrollment.infrastructure.persistence.adapter;

import com.lxp.enrollment.application.port.required.EnrollCoursePort;
import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.infrastructure.persistence.entity.EnrollmentJpaEntity;
import com.lxp.enrollment.infrastructure.persistence.repository.EnrollmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollCourseAdapter implements EnrollCoursePort {
    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public Enrollment save(Enrollment enrollment) {
        EnrollmentJpaEntity entity = EnrollmentJpaEntity.from(enrollment);
        enrollmentJpaRepository.save(entity);
        return entity.toDomain();
    }
}
