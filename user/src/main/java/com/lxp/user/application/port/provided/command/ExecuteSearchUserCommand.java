package com.lxp.user.application.port.provided.command;

import com.lxp.common.application.cqrs.Command;

public record ExecuteSearchUserCommand(String userId) implements Command {
}
