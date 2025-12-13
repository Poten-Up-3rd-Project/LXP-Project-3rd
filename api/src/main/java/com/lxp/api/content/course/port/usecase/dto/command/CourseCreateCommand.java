package com.lxp.api.content.course.port.usecase.dto.command;

import com.lxp.common.application.cqrs.Command;
import com.lxp.common.enums.Level;

import java.util.List;

//TODO("InstructorId 따로 가져오기")
public record CourseCreateCommand(
        String instructorId,
        String title,
        String description,
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