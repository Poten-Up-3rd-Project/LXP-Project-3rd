package com.lxp.enrollment.infrastructure.persistence.entity;

import com.lxp.common.infrastructure.persistence.BaseVersionedJpaEntity;
import com.lxp.enrollment.application.port.query.EnrollmentResult;
import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.infrastructure.persistence.enums.EnrollmentState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "enrollment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EnrollmentJpaEntity extends BaseVersionedJpaEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EnrollmentState state;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String courseId;

    private EnrollmentJpaEntity(EnrollmentState state, String userId, String courseId) {
        this.state = state;
        this.userId = userId;
        this.courseId = courseId;
    }

    public static EnrollmentResult toResult(EnrollmentJpaEntity entity) {
        return new EnrollmentResult(
                entity.getId(),
                entity.state.toString(),
                entity.userId,
                entity.courseId
        );
    }

    public static EnrollmentJpaEntity from(Enrollment enrollment) {
        return new EnrollmentJpaEntity(
                EnrollmentState.valueOf(enrollment.state().toString()),
                enrollment.userId(),
                enrollment.courseId()
        );
    }

}
