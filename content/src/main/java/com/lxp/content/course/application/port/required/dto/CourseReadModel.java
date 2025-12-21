package com.lxp.content.course.application.port.required.dto;

import com.lxp.common.enums.Level;

import java.time.LocalDateTime;
import java.util.List;

//TODO("나중에 readModel로 분리")
public record CourseReadModel(
        String uuid,
        String instructorId,
        String instructorName,
        String thumbnailUrl,
        String title,
        String description,
        Level difficulty,
        List<TagReadModel> tags,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public record TagReadModel(
            Long id,
            String content,
            String color,
            String variant
    ){}
}
