package com.lxp.content.course.application.service;

import com.lxp.api.content.course.port.usecase.dto.query.CourseDetailQuery;
import com.lxp.api.content.course.port.usecase.dto.result.CourseDetailView;
import com.lxp.content.course.application.mapper.CourseViewMapper;
import com.lxp.content.course.application.port.provided.usecase.CourseDetailUseCase;
import com.lxp.content.course.application.port.required.TagQueryPort;
import com.lxp.content.course.application.port.required.UserQueryPort;
import com.lxp.content.course.domain.model.id.TagId;
import com.lxp.content.course.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseDetailService implements CourseDetailUseCase {
    private final CourseRepository courseRepository;
    private final UserQueryPort userQueryPort;
    private final TagQueryPort tagQueryPort;
    private final CourseViewMapper courseViewMapper;

    @Override
    public CourseDetailView handle(CourseDetailQuery query) {
        return courseRepository.findByUUID(query.courseId())
                .map(course -> courseViewMapper.toCourseDetailView(
                        course,
                        tagQueryPort.findTagByIds(course.tags().values().stream().map(TagId::value).toList()),
                        userQueryPort.getInstructorInfo(course.instructorUUID().value())
                        ))
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + query.courseId()));
    }
}
