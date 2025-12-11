package com.lxp.common.event.integration;

import com.lxp.common.application.event.IntegrationEvent;

import java.time.LocalDateTime;
import java.util.UUID;

public record TagCreatedIntegrationEvent(
        String eventId,
        Long tagId,
        String name,
        String color,
        String variant,
        String state,
        LocalDateTime occurredAt
) implements IntegrationEvent {


    public static TagCreatedIntegrationEvent of(Long tagId, String name, String color, String variant, String state) {
        return new TagCreatedIntegrationEvent(
                UUID.randomUUID().toString(),
                tagId,
                name,
                color,
                variant,
                state,
                LocalDateTime.now()
        );
    }
    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }

    @Override
    public String getEventType() {
        return this.getClass().getName();
    }

    @Override
    public String getSource() {
        return "lxp.tag.service";
    }

    @Override
    public int getVersion() {
        return IntegrationEvent.super.getVersion();
    }

    @Override
    public String getCorrelationId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getCausationId() {
        return IntegrationEvent.super.getCausationId();
    }
}
