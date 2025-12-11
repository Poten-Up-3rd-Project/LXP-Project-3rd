package com.lxp.api.content.course.port.dto.command;

import com.lxp.common.application.cqrs.Command;
import com.lxp.common.enums.Level;

import java.util.List;

public record CourseCreateCommand(
        String title,
        String description,
        String instructorId,
        String thumbnailUrl,
        Level level,
        List<Long> tags,
        List<SectionCreateCommand> sections
) implements Command {
    public record SectionCreateCommand(
            String title,
            List<LectureCreateCommand> lectures
    ) {}

}