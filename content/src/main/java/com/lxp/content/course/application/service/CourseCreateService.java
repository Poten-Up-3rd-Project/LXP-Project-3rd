package com.lxp.content.course.application.service;

import com.lxp.api.user.port.dto.result.UserInfoResponse;
import com.lxp.api.user.port.external.ExternalUserInfoPort;
import com.lxp.common.application.port.out.DomainEventPublisher;
import com.lxp.content.course.application.mapper.CourseResultMapper;
import com.lxp.api.content.course.port.dto.command.CourseCreateCommand;
import com.lxp.api.content.course.port.dto.result.CourseInfoResult;
import com.lxp.content.course.application.port.provided.usecase.CourseCreateUseCase;
import com.lxp.content.course.application.port.required.UserQueryPort;
import com.lxp.content.course.application.port.required.dto.InstructorInfo;
import com.lxp.content.course.domain.model.Course;
import com.lxp.content.course.domain.repository.CourseRepository;
import com.lxp.content.course.domain.service.CourseCreateDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class CourseCreateService implements CourseCreateUseCase {
    private final CourseRepository courseRepository;
    private final CourseCreateDomainService courseCreateDomainService;
    private final CourseResultMapper resultMapper;
    private final DomainEventPublisher domainEventPublisher;
    private final UserQueryPort userQueryPort;

    @Override
    public CourseInfoResult handle(CourseCreateCommand command) {

        InstructorInfo instructorInfo = userQueryPort.getInstructorInfo(command.instructorId());
        // user 가져오기
        Course course = courseRepository.save(
                courseCreateDomainService.create(command,instructorInfo)
        );

        course.getDomainEvents().forEach(domainEventPublisher::publish);
        course.clearDomainEvents();
        return resultMapper.toInfoResult(course);
    }
}
