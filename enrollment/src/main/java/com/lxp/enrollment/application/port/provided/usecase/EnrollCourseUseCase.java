package com.lxp.enrollment.application.port.provided.usecase;

import com.lxp.api.enrollment.dto.command.EnrollCourseCommand;

public interface EnrollCourseUseCase {
    void enroll(EnrollCourseCommand command);
}
