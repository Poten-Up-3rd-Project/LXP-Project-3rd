package com.lxp.content.course.interfaces.web.mapper;

import com.lxp.api.content.course.port.usecase.dto.command.CourseCreateCommand;
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

}
