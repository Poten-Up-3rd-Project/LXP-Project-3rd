package com.lxp.user.application.port.provided.command;

import com.lxp.common.application.cqrs.Command;

import java.util.List;

public record ExecuteUpdateUserCommand(String userId, String name, String level, List<Long> tags, String job) implements Command {
}
