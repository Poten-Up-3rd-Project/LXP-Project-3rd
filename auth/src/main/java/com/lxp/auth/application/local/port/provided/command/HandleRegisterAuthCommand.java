package com.lxp.auth.application.local.port.provided.command;

import com.lxp.common.application.cqrs.Command;

import java.util.List;

public record HandleRegisterAuthCommand(
    String email,
    String password,
    String name,
    String role,
    List<Long> tags,
    String level,
    String job
) implements Command {
}
