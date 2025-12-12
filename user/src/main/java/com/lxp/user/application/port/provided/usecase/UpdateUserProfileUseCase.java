package com.lxp.user.application.port.provided.usecase;

import com.lxp.common.application.port.in.CommandWithResultUseCase;
import com.lxp.user.application.port.provided.command.ExecuteUpdateUserCommand;
import com.lxp.user.application.port.provided.dto.UserInfoDto;

@FunctionalInterface
public interface UpdateUserProfileUseCase extends CommandWithResultUseCase<ExecuteUpdateUserCommand, UserInfoDto> {
}
