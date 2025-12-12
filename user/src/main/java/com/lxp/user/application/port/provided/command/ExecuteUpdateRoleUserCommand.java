package com.lxp.user.application.port.provided.command;

import com.lxp.common.application.cqrs.Command;

public record ExecuteUpdateRoleUserCommand(String userId, String email, String role, String token) implements Command {
}
