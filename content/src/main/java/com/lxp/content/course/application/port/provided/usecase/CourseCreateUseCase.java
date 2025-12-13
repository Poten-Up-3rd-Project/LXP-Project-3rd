package com.lxp.content.course.application.port.provided.usecase;

import com.lxp.api.content.course.port.usecase.dto.result.CourseDetailView;
import com.lxp.common.application.cqrs.CommandWithResultHandler;
import com.lxp.api.content.course.port.usecase.dto.command.CourseCreateCommand;

public interface CourseCreateUseCase extends CommandWithResultHandler<CourseCreateCommand, CourseDetailView> {

}

