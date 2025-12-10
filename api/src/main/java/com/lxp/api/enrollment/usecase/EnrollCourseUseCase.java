package com.lxp.api.enrollment.usecase;

import com.lxp.api.enrollment.dto.command.EnrollCourseCommand;

public interface EnrollCourseUseCase {
    void enroll(EnrollCourseCommand command);
}
