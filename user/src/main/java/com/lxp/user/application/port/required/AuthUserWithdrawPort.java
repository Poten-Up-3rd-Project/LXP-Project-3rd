package com.lxp.user.application.port.required;

import com.lxp.user.application.port.required.dto.WithdrawalRequest;

public interface AuthUserWithdrawPort {

    void invalidateUser(WithdrawalRequest request);

}
