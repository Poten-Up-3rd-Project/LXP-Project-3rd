package com.lxp.enrollment.application.service;

import com.lxp.enrollment.application.port.provided.CancelEnrollmentUseCase;
import com.lxp.enrollment.application.port.provided.command.CancelEnrollmentCommand;
import com.lxp.enrollment.application.port.provided.dto.CancelCourseResult;
import com.lxp.enrollment.application.port.required.CancelEnrollmentPort;
import com.lxp.enrollment.domain.model.Enrollment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CancelEnrollmentService implements CancelEnrollmentUseCase {
    private final CancelEnrollmentPort cancelEnrollmentPort;

    public CancelCourseResult cancel(CancelEnrollmentCommand command) {
        Enrollment enrollment = cancelEnrollmentPort.findById(command.enrollmentId());
        enrollment.cancel(LocalDateTime.now());
        cancelEnrollmentPort.cancel(enrollment);
        return new CancelCourseResult(
                enrollment.getId().value(),
                enrollment.state().toString(),
                enrollment.enrollmentDate().value()
        );
    }
}
