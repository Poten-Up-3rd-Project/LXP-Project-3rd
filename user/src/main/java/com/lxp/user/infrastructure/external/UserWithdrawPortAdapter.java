package com.lxp.user.infrastructure.external;

import com.lxp.api.auth.port.dto.command.UserWithdrawCommand;
import com.lxp.api.auth.port.external.ExternalUserWithdrawPort;
import com.lxp.user.application.port.required.AuthUserWithdrawPort;
import com.lxp.user.application.port.required.dto.WithdrawalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserWithdrawPortAdapter implements AuthUserWithdrawPort {

    private final ExternalUserWithdrawPort externalUserWithdrawPort;

    @Override
    public void invalidateUser(WithdrawalRequest request) {
        externalUserWithdrawPort.invalidate(new UserWithdrawCommand(request.cookie()));
    }
}
