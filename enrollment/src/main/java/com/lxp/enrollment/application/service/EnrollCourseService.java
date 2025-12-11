package com.lxp.enrollment.application.service;

import com.lxp.api.content.course.port.external.ExternalCourseInfoPort;
import com.lxp.api.user.port.external.ExternalUserStatusPort;
import com.lxp.common.application.port.out.DomainEventPublisher;
import com.lxp.enrollment.application.port.provided.dto.command.EnrollCourseCommand;
import com.lxp.enrollment.application.port.provided.dto.enums.UserStatus;
import com.lxp.enrollment.application.port.provided.dto.result.EnrollCourseResult;
import com.lxp.enrollment.application.port.required.EnrollCoursePort;
import com.lxp.enrollment.application.usecase.EnrollCourseUseCase;
import com.lxp.enrollment.domain.exception.EnrollmentErrorCode;
import com.lxp.enrollment.domain.exception.EnrollmentException;
import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.domain.model.vo.CourseId;
import com.lxp.enrollment.domain.model.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollCourseService implements EnrollCourseUseCase {
    private final EnrollCoursePort enrollCoursePort;
    private final ExternalUserStatusPort externalUserStatusPort;
    private final ExternalCourseInfoPort externalCourseInfoPort;
    private final DomainEventPublisher domainEventPublisher;

    public EnrollCourseResult enroll(EnrollCourseCommand command) {

        String status = externalUserStatusPort.getStatusByUserId(command.userId())
                .orElseThrow(() -> new EnrollmentException(EnrollmentErrorCode.INVALID_USER_ENROLL_FAIL));
        if (!UserStatus.from(status).canEnroll()) {
            throw new EnrollmentException(EnrollmentErrorCode.INVALID_USER_ENROLL_FAIL);
        }

        // Course 존재 여부 판단
        externalCourseInfoPort.getCourseInfo(command.courseId())
                .orElseThrow(() -> new EnrollmentException(EnrollmentErrorCode.INVALID_COURSE_ENROLL_FAIL));

        Enrollment enrollment = Enrollment.create(
                new UserId(command.userId()),
                new CourseId(command.courseId())
        );

        Enrollment saved = enrollCoursePort.save(enrollment);
        enrollment.getDomainEvents().forEach(domainEventPublisher::publish);
        enrollment.clearDomainEvents();

        return new EnrollCourseResult(
                saved.getId().value(),
                saved.state().toString(),
                saved.enrollmentDate().value()
        );
    }
}
