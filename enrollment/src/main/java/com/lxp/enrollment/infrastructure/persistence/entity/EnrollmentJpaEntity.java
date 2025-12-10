package com.lxp.enrollment.infrastructure.persistence.entity;

import com.lxp.common.infrastructure.persistence.BaseVersionedJpaEntity;
import com.lxp.enrollment.application.port.query.EnrollmentResult;
import com.lxp.enrollment.infrastructure.persistence.enums.EnrollmentState;
import jakarta.persistence.*;

@Entity
@Table(name = "enrollment")
public class EnrollmentJpaEntity extends BaseVersionedJpaEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EnrollmentState state;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String courseId;

    public static EnrollmentResult toResult(EnrollmentJpaEntity entity) {
        return new EnrollmentResult(
                entity.getId(),
                entity.state.toString(),
                entity.userId,
                entity.courseId
        );
    }
}
