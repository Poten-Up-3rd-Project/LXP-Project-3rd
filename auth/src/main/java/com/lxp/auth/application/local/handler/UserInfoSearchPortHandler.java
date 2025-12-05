package com.lxp.auth.application.local.handler;

import com.lxp.auth.application.local.port.required.command.HandleUserInfoCommand;
import com.lxp.user.application.user.port.provided.dto.UserAuthResponse;
import com.lxp.user.application.user.port.provided.external.ExternalUserAuthPort;
import org.springframework.stereotype.Component;

@Component
public class UserInfoSearchPortHandler {

    private final ExternalUserAuthPort externalUserAuthPort;

    public UserInfoSearchPortHandler(ExternalUserAuthPort externalUserAuthPort) {
        this.externalUserAuthPort = externalUserAuthPort;
    }

    public HandleUserInfoCommand getUserInfo(String userId) {
        UserAuthResponse userAuthResponse = externalUserAuthPort.userAuth(userId);
        return new HandleUserInfoCommand(userAuthResponse.userId(), userAuthResponse.email(), userAuthResponse.role());
    }

    public HandleUserInfoCommand getUserInfoByEmail(String email) {
        UserAuthResponse userInfoByEmail = externalUserAuthPort.getUserInfoByEmail(email);
        return new HandleUserInfoCommand(userInfoByEmail.userId(), userInfoByEmail.email(), userInfoByEmail.role());
    }
}
