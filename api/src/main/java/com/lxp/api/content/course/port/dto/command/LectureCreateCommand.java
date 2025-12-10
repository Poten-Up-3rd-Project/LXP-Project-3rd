package com.lxp.api.content.course.port.dto.command;

public record LectureCreateCommand(
        String title,
        String videoUrl
) {
}
