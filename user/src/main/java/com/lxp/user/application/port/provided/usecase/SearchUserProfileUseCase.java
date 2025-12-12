package com.lxp.user.application.port.provided.usecase;

import com.lxp.common.application.port.in.CommandWithResultUseCase;
import com.lxp.user.application.port.provided.command.ExecuteSearchUserCommand;
import com.lxp.user.application.port.provided.dto.UserInfoDto;

@FunctionalInterface
public interface SearchUserProfileUseCase extends CommandWithResultUseCase<ExecuteSearchUserCommand, UserInfoDto> {
}
