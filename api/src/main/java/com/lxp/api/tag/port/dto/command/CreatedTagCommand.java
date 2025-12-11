package com.lxp.api.tag.port.dto.command;

import com.lxp.common.application.cqrs.Command;

public record CreatedTagCommand(
        Long tagCategoryId,
        String name,
        String state, // ACTIVE, INACTIVE
        String color,
        String variant

) implements Command {
}
