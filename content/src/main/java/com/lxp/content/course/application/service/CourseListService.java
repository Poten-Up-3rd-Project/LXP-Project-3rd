package com.lxp.content.course.application.service;


import com.lxp.api.content.course.port.usecase.dto.query.CourseListQuery;
import com.lxp.api.content.course.port.usecase.dto.result.CourseView;
import com.lxp.common.domain.pagination.Page;
import com.lxp.content.course.application.mapper.CourseViewMapper;
import com.lxp.content.course.application.port.provided.usecase.CourseListUseCase;
import com.lxp.content.course.application.port.required.CourseReadModelPort;
import com.lxp.content.course.application.port.required.dto.CourseReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class CourseListService implements CourseListUseCase {
    private final CourseReadModelPort courseReadModelPort;
    private final CourseViewMapper courseViewMapper;
    @Override
    public Page<CourseView> handle(CourseListQuery query) {
        Page<CourseReadModel> course = courseReadModelPort.findAll(query.pageRequest());
        return courseViewMapper.toPageView(course);
    }
}
