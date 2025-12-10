package com.lxp.content.course.application.service;

import com.lxp.content.course.application.mapper.CourseResultMapper;
import com.lxp.content.course.application.port.provided.external.ExternalCourseSummaryPort;
import com.lxp.content.course.application.port.provided.dto.result.CourseResult;
import com.lxp.content.course.domain.model.Course;
import com.lxp.content.course.domain.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExternalCourseSummaryService implements ExternalCourseSummaryPort {
    private final CourseRepository courseRepository;
    private final CourseResultMapper resultMapper;


    @Override
    public Optional<CourseResult> getCourseSummary(String courseUUID) {
        Optional<Course> courseOptional = courseRepository.findByUUID(courseUUID);
        return courseOptional.map(resultMapper::toResult);
   }
}
