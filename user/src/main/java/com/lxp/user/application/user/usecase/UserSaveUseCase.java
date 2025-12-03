package com.lxp.user.application.user.usecase;

import com.lxp.user.application.user.command.UserSaveCommand;

@FunctionalInterface
public interface UserSaveUseCase {
    void saveUser(UserSaveCommand command);
}
