package com.lxp.enrollment.application.port.provided.usecase;


import com.lxp.api.enrollment.dto.command.CancelEnrollmentCommand;
import com.lxp.api.enrollment.dto.result.CancelCourseResult;

public interface CancelEnrollmentUseCase {
    CancelCourseResult cancel(CancelEnrollmentCommand command);
}
