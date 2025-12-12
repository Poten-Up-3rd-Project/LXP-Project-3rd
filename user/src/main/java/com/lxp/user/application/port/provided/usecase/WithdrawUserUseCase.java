package com.lxp.user.application.port.provided.usecase;

import com.lxp.common.application.port.in.CommandWithResultUseCase;
import com.lxp.user.application.port.provided.command.ExecuteWithdrawUserCommand;

@FunctionalInterface
public interface WithdrawUserUseCase extends CommandWithResultUseCase<ExecuteWithdrawUserCommand, Void> {
}
