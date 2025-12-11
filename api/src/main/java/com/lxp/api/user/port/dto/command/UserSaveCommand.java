package com.lxp.api.user.port.dto.command;

import com.lxp.common.application.cqrs.Command;

import java.util.List;
import java.util.UUID;

public record UserSaveCommand(
    UUID userId,
    String name,
    String email,
    String role,

    List<Long> Tags,
    String level,
    String job
) implements Command {
}
