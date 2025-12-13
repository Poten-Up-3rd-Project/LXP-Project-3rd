package com.lxp.content.course.application.port.provided.usecase;

import com.lxp.api.content.course.port.usecase.dto.result.CourseView;
import com.lxp.common.application.port.in.QueryUseCase;

import java.util.List;

//TODO("")
public interface CoursesUseCase extends QueryUseCase<Void, List<CourseView>> {
}
