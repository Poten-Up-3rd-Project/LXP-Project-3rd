package com.lxp.api.user.port.dto.command;

import com.lxp.common.application.cqrs.Command;

import java.util.List;

public record UserRegisterCommand(
    String userId,
    String email,
    String password,
    String name,
    String role,
    List<Long> tags,
    String learnerLevel,
    String job
) implements Command {
}
