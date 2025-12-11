package com.lxp.api.user.port.external;

import com.lxp.api.user.port.dto.command.UserSaveCommand;

@FunctionalInterface
public interface ExternalUserSavePort {

    void saveUser(UserSaveCommand command);
}
