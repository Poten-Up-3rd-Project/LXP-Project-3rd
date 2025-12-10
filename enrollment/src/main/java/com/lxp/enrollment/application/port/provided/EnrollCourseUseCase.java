package com.lxp.enrollment.application.port.provided;

import com.lxp.enrollment.application.port.provided.command.EnrollCourseCommand;
import com.lxp.enrollment.application.port.provided.dto.EnrollCourseResult;

public interface EnrollCourseUseCase {
    EnrollCourseResult enroll(EnrollCourseCommand command);
}
