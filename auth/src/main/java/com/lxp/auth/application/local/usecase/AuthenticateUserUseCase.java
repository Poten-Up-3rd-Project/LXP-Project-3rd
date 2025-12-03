package com.lxp.auth.application.local.usecase;

import com.lxp.auth.application.local.command.HandleLoginCommand;

@FunctionalInterface
public interface AuthenticateUserUseCase {
    void authenticate(HandleLoginCommand command);
}
