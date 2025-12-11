package com.lxp.enrollment.application.service;

import com.lxp.api.content.course.port.dto.result.CourseInfoResult;
import com.lxp.api.content.course.port.external.ExternalCourseInfoPort;
import com.lxp.api.user.port.external.ExternalUserStatusPort;
import com.lxp.common.application.port.out.DomainEventPublisher;
import com.lxp.common.enums.Level;
import com.lxp.enrollment.application.port.provided.dto.command.EnrollCourseCommand;
import com.lxp.enrollment.application.port.provided.dto.enums.UserStatus;
import com.lxp.enrollment.application.port.provided.dto.result.EnrollCourseResult;
import com.lxp.enrollment.application.port.required.EnrollCoursePort;
import com.lxp.enrollment.domain.event.EnrollmentCreatedEvent;
import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.domain.model.vo.CourseId;
import com.lxp.enrollment.domain.model.vo.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollCourseServiceTest {
    @Mock
    private EnrollCoursePort enrollCoursePort;
    
    @Mock
    private ExternalUserStatusPort externalUserStatusPort;
    
    @Mock
    private ExternalCourseInfoPort externalCourseInfoPort;

    @Mock
    private DomainEventPublisher domainEventPublisher;
    
    @InjectMocks
    private EnrollCourseService enrollCourseService;

    private EnrollCourseCommand createCommand() {
        return new EnrollCourseCommand(
                "user-uuid",
                "course-uuid"
        );
    }

    private Enrollment createEnrollmentWithEvent() {
        return Enrollment.create(
                new UserId(UUID.randomUUID().toString()),
                new CourseId(UUID.randomUUID().toString())
        );
    }

    private EnrollCourseResult createExpectedResult() {
        return new EnrollCourseResult(
                1L,
                "ENROLLED",
                LocalDateTime.now()
        );
    }
    
    private CourseInfoResult createCourseInfoResult() {
        return new CourseInfoResult(
                UUID.randomUUID().toString(),
                1L,
                "instructor-uuid",
                "테스트 강의",
                "https://example.com/thumbnail.jpg",
                "테스트 설명",
                3000L,
                Level.JUNIOR,
                List.of(),
                List.of(1L)
        );
    }

    @Test
    @DisplayName("수강 신청 시 도메인 이벤트가 발행된다")
    void enroll_shouldPublishDomainEvent() {
        // given
        EnrollCourseCommand command = createCommand();
        Enrollment enrollmentWithEvent = createEnrollmentWithEvent();
        
        when(externalUserStatusPort.getStatusByUserId(command.userId()))
                .thenReturn(Optional.of(UserStatus.ACTIVE.name()));
        when(externalCourseInfoPort.getCourseInfo(command.courseId()))
                .thenReturn(Optional.of(createCourseInfoResult()));
        
        when(enrollCoursePort.save(any(Enrollment.class)))
                .thenReturn(Enrollment.reconstruct(
                        "enrollment-uuid",
                        1L,
                        enrollmentWithEvent.state().name(),
                        enrollmentWithEvent.userId(),
                        enrollmentWithEvent.courseId(),
                        enrollmentWithEvent.enrollmentDate().value(),
                        enrollmentWithEvent.cancelReason()
                ));

        // when
        enrollCourseService.enroll(command);
        
        // then
        verify(domainEventPublisher, times(1)).publish(any(EnrollmentCreatedEvent.class));

    }

}