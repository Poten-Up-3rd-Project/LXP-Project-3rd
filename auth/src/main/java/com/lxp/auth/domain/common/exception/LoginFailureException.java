package com.lxp.auth.domain.common.exception;

import com.lxp.common.domain.exception.DomainException;
import com.lxp.common.domain.exception.ErrorCode;

public class LoginFailureException extends DomainException {

    public LoginFailureException(ErrorCode errorCode) {
        super(errorCode);
    }
}
