package com.lxp.auth.application.local.port.required.usecase;

import com.lxp.auth.application.local.port.required.command.HandleRegisterAuthCommand;
import com.lxp.common.application.port.in.CommandWithResultUseCase;

@FunctionalInterface
public interface RegisterUserUseCase extends CommandWithResultUseCase<HandleRegisterAuthCommand, Void> {
}
