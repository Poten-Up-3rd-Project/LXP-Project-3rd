package com.lxp.content.course.interfaces.web.mapper;

import com.lxp.api.content.course.port.usecase.dto.command.CourseCreateCommand;
import com.lxp.api.content.course.port.usecase.dto.query.CourseDetailQuery;
import com.lxp.api.content.course.port.usecase.dto.query.CourseListQuery;
import com.lxp.api.content.course.port.usecase.dto.result.CourseDetailView;
import com.lxp.api.content.course.port.usecase.dto.result.CourseView;
import com.lxp.common.application.cqrs.CommandBus;
import com.lxp.common.application.cqrs.QueryBus;
import com.lxp.common.domain.pagination.Page;
import com.lxp.common.domain.pagination.PageRequest;
import com.lxp.content.course.interfaces.web.dto.response.CourseDetailResponse;
import com.lxp.content.course.interfaces.web.dto.response.CourseResponse;
import com.lxp.content.course.interfaces.web.dto.reuqest.create.CourseCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseApplicationFacade {
    private final CourseWebMapper mapper;
    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public CourseDetailResponse createCourse(String userId, CourseCreateRequest request) {
        CourseCreateCommand command = mapper.toCreateCommand(userId,request);
        CourseDetailView result = commandBus.dispatchWithResult(command);
        return mapper.toDetailResponse(result);
    }

    public Page<CourseResponse> getCourses(PageRequest pageRequest) {
        CourseListQuery query = CourseListQuery.builder()
                .pageRequest(pageRequest)
                .build();
        Page<CourseView> coursePage = queryBus.dispatch(query);
        return mapper.toPageResponse(coursePage);
    }

    public CourseDetailResponse getCourseDetail(String courseId) {
        CourseDetailQuery query = CourseDetailQuery.builder()
                .courseId(courseId)
                .build();
        CourseDetailView courseDetail = queryBus.dispatch(query);
        return mapper.toDetailResponse(courseDetail);
    }

    public Page<CourseResponse> searchCourse(PageRequest pageRequest, String keyword) {
        CourseListQuery query = CourseListQuery.builder()
                .keyword(keyword)
                .pageRequest(pageRequest)
                .build();

        Page<CourseView> coursePage = queryBus.dispatch(query);
        return mapper.toPageResponse(coursePage);
    }
}