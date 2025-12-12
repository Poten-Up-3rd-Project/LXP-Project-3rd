package com.lxp.user.application.port.required.usecase;

import com.lxp.api.auth.port.dto.result.AuthenticationResponse;
import com.lxp.common.application.port.in.CommandWithResultUseCase;
import com.lxp.user.application.port.required.command.UpdateUserRoleCommand;

@FunctionalInterface
public interface UpdateUserRoleUseCase extends CommandWithResultUseCase<UpdateUserRoleCommand, AuthenticationResponse> {
}
