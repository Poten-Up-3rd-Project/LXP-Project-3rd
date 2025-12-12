package com.lxp.user.application.port.provided.usecase;

import com.lxp.common.application.port.in.CommandWithResultUseCase;
import com.lxp.user.application.port.provided.command.UpdateUserRoleCommand;
import com.lxp.user.application.port.required.dto.AuthTokenResult;

@FunctionalInterface
public interface UpdateUserRoleUseCase extends CommandWithResultUseCase<UpdateUserRoleCommand, AuthTokenResult> {
}
