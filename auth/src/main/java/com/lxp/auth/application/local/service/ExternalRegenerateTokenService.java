package com.lxp.auth.application.local.service;

import com.lxp.api.auth.port.dto.command.RegenerateTokenCommand;
import com.lxp.api.auth.port.dto.result.AuthenticationResponse;
import com.lxp.api.auth.port.external.ExternalRegenerateTokenPort;
import com.lxp.auth.infrastructure.mapper.AuthUserMapper;
import com.lxp.auth.domain.common.model.vo.AuthTokenInfo;
import com.lxp.auth.domain.common.policy.JwtPolicy;
import com.lxp.auth.domain.common.policy.TokenRevocationPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExternalRegenerateTokenService implements ExternalRegenerateTokenPort {

    private final JwtPolicy jwtTokenProvider;
    private final AuthUserMapper authUserMapper;
    private final TokenRevocationPolicy tokenRevocationPolicy;
    private final JwtPolicy jwtPolicy;

    @Override
    public Optional<AuthenticationResponse> regenerateToken(RegenerateTokenCommand command) {
        AuthTokenInfo token = jwtTokenProvider.createToken(authUserMapper.to(command));

        long remainingSeconds = jwtPolicy.getRemainingSeconds(command.token());
        if (remainingSeconds > 0) {
            tokenRevocationPolicy.revokeAccessToken(command.token(), remainingSeconds);
        }
        return Optional.of(authUserMapper.to(token));
    }
}
