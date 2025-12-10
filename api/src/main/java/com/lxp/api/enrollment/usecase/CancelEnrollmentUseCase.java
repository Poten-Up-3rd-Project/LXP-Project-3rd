package com.lxp.api.enrollment.usecase;


import com.lxp.api.enrollment.dto.command.CancelEnrollmentCommand;
import com.lxp.api.enrollment.dto.result.CancelCourseResult;

public interface CancelEnrollmentUseCase {
    CancelCourseResult cancel(CancelEnrollmentCommand command);
}
