package com.lxp.enrollment.application.service;

import com.lxp.enrollment.application.port.provided.dto.command.CancelEnrollmentCommand;
import com.lxp.enrollment.application.port.provided.dto.result.CancelEnrollmentResult;
import com.lxp.enrollment.application.port.required.CancelEnrollmentPort;
import com.lxp.enrollment.application.usecase.CancelEnrollmentUseCase;
import com.lxp.enrollment.domain.model.Enrollment;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CancelEnrollmentService implements CancelEnrollmentUseCase {
    private final CancelEnrollmentPort cancelEnrollmentPort;

    public CancelEnrollmentResult cancel(CancelEnrollmentCommand command) {
        Enrollment enrollment = cancelEnrollmentPort.findById(command.enrollmentId());
        enrollment.cancel(LocalDateTime.now(), command.reason());
        cancelEnrollmentPort.cancel(enrollment);
        return new CancelEnrollmentResult(
                enrollment.getId().value(),
                enrollment.state().toString(),
                enrollment.enrollmentDate().value()
        );
    }
}
