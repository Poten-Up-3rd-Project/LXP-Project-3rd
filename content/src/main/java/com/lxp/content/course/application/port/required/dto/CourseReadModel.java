package com.lxp.content.course.application.port.required.dto;

import com.lxp.common.enums.Level;

import java.time.LocalDateTime;
import java.util.List;

public record CourseReadModel(
        String uuid,
        String instructorId,
        String instructorName,
        String thumbnailUrl,
        String title,
        String description,
        Level difficulty,
        List<Long> tags,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
