package com.lxp.auth.application.local.port.required.usecase;

import com.lxp.auth.application.local.port.required.command.HandleLoginCommand;
import com.lxp.auth.application.local.port.required.dto.AuthTokenInfo;

@FunctionalInterface
public interface AuthenticateUserUseCase {
    AuthTokenInfo authenticate(HandleLoginCommand command);
}
