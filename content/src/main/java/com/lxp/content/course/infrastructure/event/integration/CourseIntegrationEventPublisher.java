package com.lxp.content.course.infrastructure.event.integration;

import com.lxp.common.event.integration.CourseCreatedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseIntegrationEventPublisher {

    //TODO: 추후 메시지 브로커 연동 시 수정 필요
    private final ApplicationEventPublisher eventPublisher;

    public void publish(CourseCreatedIntegrationEvent event) {
        eventPublisher.publishEvent(event);
    }
}
