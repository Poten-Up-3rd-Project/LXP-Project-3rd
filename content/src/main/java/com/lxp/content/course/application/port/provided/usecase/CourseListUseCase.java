package com.lxp.content.course.application.port.provided.usecase;

import com.lxp.api.content.course.port.usecase.dto.query.CourseListQuery;
import com.lxp.api.content.course.port.usecase.dto.result.CourseView;
import com.lxp.common.application.cqrs.QueryHandler;
import com.lxp.common.domain.pagination.Page;

public interface CourseListUseCase extends QueryHandler<CourseListQuery, Page<CourseView>> {
}
