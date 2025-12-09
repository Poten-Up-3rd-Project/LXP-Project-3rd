package com.lxp.common.event.integration;

import java.time.LocalDateTime;
import java.util.List;

public record CourseCreatedIntegrationEvent(
        String eventId,
        String courseUuid,
        String instructorUuid,
        String title,
        String description,
        String thumbnailUrl,
        String difficulty,
        List<Long> tagIds,
        LocalDateTime occurredAt
) {
}