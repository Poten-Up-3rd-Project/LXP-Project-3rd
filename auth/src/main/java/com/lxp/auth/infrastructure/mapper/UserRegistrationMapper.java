package com.lxp.auth.infrastructure.mapper;

import com.lxp.api.user.port.dto.command.UserSaveCommand;
import com.lxp.auth.application.local.port.required.dto.UserRegistrationInfo;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationMapper {

    public UserSaveCommand toSaveCommand(UserRegistrationInfo info) {
        return new UserSaveCommand(
            info.userId(),
            info.name(),
            info.email(),
            info.role(),
            info.tags(),
            info.level(),
            info.job()
        );
    }

}
