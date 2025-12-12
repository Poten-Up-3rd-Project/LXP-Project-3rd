package com.lxp.auth.application.local.service;

import com.lxp.auth.application.local.port.provided.usecase.LogoutUserUseCase;
import com.lxp.auth.application.local.port.provided.command.HandleLogoutCommand;
import com.lxp.auth.domain.common.policy.JwtPolicy;
import com.lxp.auth.domain.common.policy.TokenRevocationPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LogoutService implements LogoutUserUseCase {

    private final JwtPolicy jwtPolicy;
    private final TokenRevocationPolicy tokenRevocationPolicy;

    @Override
    public Void execute(HandleLogoutCommand command) {
        String token = command.accessToken();
        long remainingSeconds = jwtPolicy.getRemainingSeconds(token);
        if (remainingSeconds > 0) {
            tokenRevocationPolicy.revokeAccessToken(token, remainingSeconds);
        }
        return null;
    }
}
