package com.lxp.enrollment.application.service;

import com.lxp.enrollment.application.port.provided.EnrollCourseUseCase;
import com.lxp.enrollment.application.port.provided.command.EnrollCourseCommand;
import com.lxp.enrollment.application.port.provided.dto.EnrollCourseResult;
import com.lxp.enrollment.application.port.required.EnrollCoursePort;
import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.domain.model.vo.CourseId;
import com.lxp.enrollment.domain.model.vo.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollCourseService implements EnrollCourseUseCase {
    private final EnrollCoursePort enrollCoursePort;

    public EnrollCourseResult enroll(EnrollCourseCommand command) {
        Enrollment enrollment = Enrollment.create(new UserId(command.userId()), new CourseId(command.courseId()));
        enrollCoursePort.save(enrollment);
        return new EnrollCourseResult(
                enrollment.getId().value(),
                enrollment.state().toString()
        );
    }
}

