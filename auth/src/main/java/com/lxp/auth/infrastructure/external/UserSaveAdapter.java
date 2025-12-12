package com.lxp.auth.infrastructure.external;

import com.lxp.api.user.port.dto.command.UserSaveCommand;
import com.lxp.api.user.port.external.ExternalUserSavePort;
import com.lxp.auth.application.local.port.required.UserSavePort;
import com.lxp.auth.application.local.port.required.dto.UserRegistrationInfo;
import com.lxp.auth.infrastructure.mapper.UserRegistrationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSaveAdapter implements UserSavePort {

    private final ExternalUserSavePort externalUserSavePort;
    private final UserRegistrationMapper userRegistrationMapper;

    @Override
    public void save(UserRegistrationInfo info) {
        UserSaveCommand saveCommand = userRegistrationMapper.toSaveCommand(info);
        externalUserSavePort.saveUser(saveCommand);
    }
}
