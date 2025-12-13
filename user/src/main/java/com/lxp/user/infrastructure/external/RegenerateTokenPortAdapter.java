package com.lxp.user.infrastructure.external;

import com.lxp.api.auth.port.dto.command.RegenerateTokenCommand;
import com.lxp.api.auth.port.dto.result.AuthenticationResponse;
import com.lxp.api.auth.port.external.ExternalRegenerateTokenPort;
import com.lxp.user.application.port.required.AuthTokenProviderPort;
import com.lxp.user.application.port.required.dto.AuthTokenResult;
import com.lxp.user.application.port.required.dto.TokenRegenerationDto;
import com.lxp.user.domain.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegenerateTokenPortAdapter implements AuthTokenProviderPort {

    private final ExternalRegenerateTokenPort externalRegenerateTokenPort;

    @Override
    public AuthTokenResult regenerateToken(TokenRegenerationDto command) {
        AuthenticationResponse authenticationResponse = externalRegenerateTokenPort.regenerateToken(new RegenerateTokenCommand(
            command.userId(), command.email(), command.role(), command.token()
        )).orElseThrow(UserNotFoundException::new);

        return new AuthTokenResult(authenticationResponse.accessToken(), authenticationResponse.expiresIn());
    }
}
