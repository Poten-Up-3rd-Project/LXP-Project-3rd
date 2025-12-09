package com.lxp.content.course.application.service;

import com.lxp.content.course.application.mapper.CourseResultMapper;
import com.lxp.content.course.application.port.provided.dto.command.CourseCreateCommand;
import com.lxp.content.course.application.port.provided.dto.result.CourseInfoResult;
import com.lxp.content.course.application.port.provided.usecase.CourseCreateUseCase;
import com.lxp.content.course.domain.model.Course;
import com.lxp.content.course.domain.repository.CourseRepository;
import com.lxp.content.course.domain.service.CourseCreateDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseCreateService implements CourseCreateUseCase {
    private final CourseRepository courseRepository;
    private final CourseCreateDomainService courseCreateDomainService;
    private final CourseResultMapper resultMapper;
    // TODO(userPort 포트주입)

    @Override
    public CourseInfoResult handle(CourseCreateCommand command) {
        // user 가져오기
        Course course = courseRepository.save(
                courseCreateDomainService.create(command)
        );

        return resultMapper.toResult(course);
    }
}
