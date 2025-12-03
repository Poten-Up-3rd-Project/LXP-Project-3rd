package com.lxp.auth.application.local.usecase;

import com.lxp.auth.application.local.command.HandleUserRegisterCommand;

@FunctionalInterface
public interface RegisterUserUseCase {
    void register(HandleUserRegisterCommand command);
}
