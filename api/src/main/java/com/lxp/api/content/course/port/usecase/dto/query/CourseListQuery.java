package com.lxp.api.content.course.port.usecase.dto.query;

import com.lxp.api.content.course.port.usecase.dto.result.CourseView;
import com.lxp.common.application.cqrs.Query;
import com.lxp.common.domain.pagination.Page;
import com.lxp.common.domain.pagination.PageRequest;
import lombok.Builder;

@Builder
public record CourseListQuery(
        String keyword,
        PageRequest pageRequest
) implements Query<Page<CourseView>> {
}