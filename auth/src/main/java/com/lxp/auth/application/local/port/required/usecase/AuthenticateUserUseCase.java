package com.lxp.auth.application.local.port.required.usecase;

import com.lxp.auth.application.local.port.required.command.HandleLoginCommand;

@FunctionalInterface
public interface AuthenticateUserUseCase {
    String authenticate(HandleLoginCommand command);
}
