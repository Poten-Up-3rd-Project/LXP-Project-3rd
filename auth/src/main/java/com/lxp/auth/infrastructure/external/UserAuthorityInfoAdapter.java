package com.lxp.auth.infrastructure.external;

import com.lxp.api.user.port.dto.result.UserAuthResponse;
import com.lxp.api.user.port.external.ExternalUserAuthPort;
import com.lxp.auth.application.local.port.required.UserAuthSearchPort;
import com.lxp.auth.application.local.port.required.dto.AuthDomainInfo;
import com.lxp.auth.domain.common.exception.EmailNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuthorityInfoAdapter implements UserAuthSearchPort {

    private final ExternalUserAuthPort externalUserAuthPort;

    @Override
    public AuthDomainInfo retrieveAuthorityByUserId(String userId) {
        UserAuthResponse userAuthResponse = externalUserAuthPort.userAuth(userId)
            .orElseThrow();
        return new AuthDomainInfo(userAuthResponse.userId(), userAuthResponse.email(), userAuthResponse.role());
    }

    @Override
    public AuthDomainInfo retrieveAuthorityByEmail(String email) {
        UserAuthResponse userAuthResponse = externalUserAuthPort.getUserInfoByEmail(email)
            .orElseThrow(EmailNotFoundException::new);
        return new AuthDomainInfo(userAuthResponse.userId(), userAuthResponse.email(), userAuthResponse.role());
    }
}
