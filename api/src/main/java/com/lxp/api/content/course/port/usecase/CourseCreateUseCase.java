package com.lxp.api.content.course.port.usecase;

import com.lxp.common.application.cqrs.CommandWithResultHandler;
import com.lxp.api.content.course.port.dto.command.CourseCreateCommand;
import com.lxp.api.content.course.port.dto.result.CourseInfoResult;

public interface CourseCreateUseCase extends CommandWithResultHandler<CourseCreateCommand, CourseInfoResult> {

}

