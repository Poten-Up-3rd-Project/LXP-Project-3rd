package com.lxp.api.content.course.port.usecase.dto.query;

import com.lxp.api.content.course.port.usecase.dto.result.CourseDetailView;
import com.lxp.common.application.cqrs.Query;
import lombok.Builder;

@Builder
public record CourseDetailQuery(String courseId) implements Query<CourseDetailView> {
}
