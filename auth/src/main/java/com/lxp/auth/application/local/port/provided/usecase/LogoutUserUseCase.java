package com.lxp.auth.application.local.port.provided.usecase;

import com.lxp.auth.application.local.port.provided.command.HandleLogoutCommand;
import com.lxp.common.application.port.in.CommandWithResultUseCase;

@FunctionalInterface
public interface LogoutUserUseCase extends CommandWithResultUseCase<HandleLogoutCommand, Void> {
}
