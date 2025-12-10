package com.lxp.enrollment.domain.model;

import com.lxp.common.domain.event.AggregateRoot;
import com.lxp.enrollment.domain.exception.EnrollmentErrorCode;
import com.lxp.enrollment.domain.exception.EnrollmentException;
import com.lxp.enrollment.domain.model.enums.EnrollmentState;
import com.lxp.enrollment.domain.model.vo.CourseId;
import com.lxp.enrollment.domain.model.vo.EnrollmentDate;
import com.lxp.enrollment.domain.model.vo.EnrollmentId;
import com.lxp.enrollment.domain.model.vo.UserId;

import java.time.LocalDateTime;
import java.util.Objects;

public class Enrollment extends AggregateRoot<EnrollmentId> {
    private EnrollmentId enrollmentId;
    private EnrollmentState state;
    private UserId userId;
    private CourseId courseId;
    private EnrollmentDate enrollmentDate;

    private Enrollment() {}

    public Enrollment(EnrollmentId enrollmentId, EnrollmentState state, UserId userId, CourseId courseId, EnrollmentDate enrollmentDate) {
        this.enrollmentId = enrollmentId;
        this.state = state;
        this.userId = userId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public EnrollmentId getId() {
        return this.enrollmentId;
    }

    private Enrollment(EnrollmentState state, UserId userId, CourseId courseId) {
        this.state = state;
        this.userId = userId;
        this.courseId = courseId;
        this.enrollmentDate = new EnrollmentDate(LocalDateTime.now());
    }

    public static Enrollment reconstruct(
            long enrollmentId,
            String state,
            String userId,
            String courseId,
            LocalDateTime createdAt
    ) {
        return new Enrollment(
                new EnrollmentId(enrollmentId),
                EnrollmentState.valueOf(state),
                new UserId(userId),
                new CourseId(courseId),
                new EnrollmentDate(createdAt)
        );
    }

    public static Enrollment create(UserId userId, CourseId courseId) {
        Objects.requireNonNull(userId, "UserId must not be null");
        Objects.requireNonNull(courseId, "CourseId must not be null");
        return new Enrollment(EnrollmentState.ENROLLED, userId, courseId);
    }

    public void complete() {
        if (this.state == EnrollmentState.CANCELLED) {
            throw new EnrollmentException(EnrollmentErrorCode.CAN_NOT_CANCEL_COMPLETED_ENROLLMENT);
        }
        if (this.state == EnrollmentState.COMPLETED) {
            return;
        }

        this.state = EnrollmentState.COMPLETED;
    }

    public void cancel(LocalDateTime now) {
        Objects.requireNonNull(now, "now must not be null");

        if (this.state == EnrollmentState.CANCELLED) {
            return;
        }

        if (this.state == EnrollmentState.COMPLETED) {
            throw new EnrollmentException(EnrollmentErrorCode.CAN_NOT_COMPLETE_CANCEL_ENROLLMENT);
        }

        if (!enrollmentDate.canCancel(now)) {
            throw new EnrollmentException(EnrollmentErrorCode.CAN_NOT_CANCEL_ENROLLMENT);
        }

        this.state = EnrollmentState.CANCELLED;
    }

    public EnrollmentState state() {
        return this.state;
    }

    public EnrollmentDate enrollmentDate() {
        return this.enrollmentDate;
    }

    public String userId() {
        return this.userId.toString();
    }

    public String courseId() {
        return this.courseId.toString();
    }
}
