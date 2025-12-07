package com.lxp.content.course.application.service;

import com.lxp.content.course.application.port.provided.dto.command.CourseCreateCommand;
import com.lxp.content.course.application.port.provided.usecase.CourseCreateUseCase;
import com.lxp.content.course.domain.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CourseCreateService implements CourseCreateUseCase {
    private CourseRepository courseRepository;

    @Override
    public Long execute(CourseCreateCommand input) {
        return 0L;
    }
}
