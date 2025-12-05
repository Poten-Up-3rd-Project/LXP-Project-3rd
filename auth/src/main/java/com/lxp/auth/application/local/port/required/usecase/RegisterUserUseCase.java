package com.lxp.auth.application.local.port.required.usecase;

import com.lxp.auth.application.local.port.required.command.HandleUserRegisterCommand;

@FunctionalInterface
public interface RegisterUserUseCase {
    void register(HandleUserRegisterCommand command);
}
