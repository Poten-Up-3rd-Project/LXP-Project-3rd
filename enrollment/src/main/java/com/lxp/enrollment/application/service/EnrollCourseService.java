package com.lxp.enrollment.application.service;

import com.lxp.api.content.course.port.external.ExternalCourseInfoPort;
import com.lxp.api.user.port.external.ExternalUserStatusPort;
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

    public EnrollCourseResult enroll(EnrollCourseCommand command) {

        String status = externalUserStatusPort.getStatusByUserId(command.userId())
                .orElseThrow(() -> new EnrollmentException(EnrollmentErrorCode.INVALID_USER_ENROLL_FAIL));
        if (!UserStatus.from(status).canEnroll()) {
            throw new EnrollmentException(EnrollmentErrorCode.INVALID_USER_ENROLL_FAIL);
        }

        externalCourseInfoPort.getCourseInfo(command.courseId()) // 존재 여부만 판별 할 수 있는 port가 없어서 해당 port 사용됨
                .orElseThrow(() -> new EnrollmentException(EnrollmentErrorCode.INVALID_COURSE_ENROLL_FAIL));

        Enrollment enrollment = Enrollment.create(
                new UserId(command.userId()),
                new CourseId(command.courseId())
        );

        Enrollment saved = enrollCoursePort.save(enrollment);

        return new EnrollCourseResult(
                saved.getId().value(),
                saved.state().toString(),
                saved.enrollmentDate().value()
        );
    }
}
