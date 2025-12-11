package com.lxp.enrollment.infrastructure.persistence.adapter;

import com.lxp.common.domain.pagination.Page;
import com.lxp.enrollment.application.port.query.EnrollmentResult;
import com.lxp.enrollment.application.port.required.EnrollmentQueryPort;
import com.lxp.enrollment.infrastructure.persistence.entity.EnrollmentJpaEntity;
import com.lxp.enrollment.infrastructure.persistence.enums.EnrollmentState;
import com.lxp.enrollment.infrastructure.persistence.mapper.PageConverter;
import com.lxp.enrollment.infrastructure.persistence.repository.EnrollmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentQueryAdapter implements EnrollmentQueryPort {
    private final EnrollmentJpaRepository enrollmentJpaRepository;

    public Page<EnrollmentResult> findEnrollments(
            String userId,
            String state,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "enrollmentId"));

        EnrollmentState enrollmentState = EnrollmentState.from(state);
        if (enrollmentState == null) {
            return PageConverter.toPage(
                    enrollmentJpaRepository.findByUserId(userId, pageable),
                    EnrollmentJpaEntity::toResult
            );
        }

        return PageConverter.toPage(
                enrollmentJpaRepository.findByUserIdAndState(userId, enrollmentState, pageable),
                EnrollmentJpaEntity::toResult
        );
    }
}
