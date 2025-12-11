package com.lxp.tag.infrastructure.event;

import com.lxp.common.application.event.IntegrationEvent;
import com.lxp.common.application.port.out.IntegrationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagIntegrationEventPublisher implements IntegrationEventPublisher {
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publish(IntegrationEvent event) {
        eventPublisher.publishEvent(event);
    }

    @Override
    public void publish(String topic, IntegrationEvent event) {

    }
}
