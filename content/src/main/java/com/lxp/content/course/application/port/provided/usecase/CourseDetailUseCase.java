package com.lxp.content.course.application.port.provided.usecase;

import com.lxp.api.content.course.port.usecase.dto.query.CourseDetailQuery;
import com.lxp.api.content.course.port.usecase.dto.result.CourseDetailView;
import com.lxp.common.application.cqrs.QueryHandler;

public interface CourseDetailUseCase extends QueryHandler<CourseDetailQuery, CourseDetailView> {
}
