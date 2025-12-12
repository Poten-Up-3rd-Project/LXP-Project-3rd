package com.lxp.auth.application.local.port.provided.usecase;

import com.lxp.auth.application.local.port.provided.command.HandleLoginCommand;
import com.lxp.auth.domain.common.model.vo.AuthTokenInfo;
import com.lxp.common.application.port.in.CommandWithResultUseCase;

@FunctionalInterface
public interface AuthenticateUserUseCase extends CommandWithResultUseCase<HandleLoginCommand, AuthTokenInfo> {
}
