package com.lxp.auth.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxp.common.application.event.IntegrationEvent;
import com.lxp.common.application.port.out.IntegrationEventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AuthIntegrationPublisher implements IntegrationEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;
    private final ObjectMapper objectMapper;

    public AuthIntegrationPublisher(ApplicationEventPublisher applicationEventPublisher, ObjectMapper objectMapper) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.objectMapper = objectMapper;
    }

    @Override
    public void publish(IntegrationEvent integrationEvent) {
        applicationEventPublisher.publishEvent(integrationEvent);
    }

    @Override
    public void publish(String s, IntegrationEvent integrationEvent) {
        applicationEventPublisher.publishEvent(integrationEvent);
    }
}
