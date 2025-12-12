package com.lxp.auth.application.local.port.provided.command;

import com.lxp.common.application.cqrs.Command;

public record HandleLogoutCommand(String accessToken) implements Command {
}
