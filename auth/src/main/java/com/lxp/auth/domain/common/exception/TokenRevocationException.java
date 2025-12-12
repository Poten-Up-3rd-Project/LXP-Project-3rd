package com.lxp.auth.domain.common.exception;

public class TokenRevocationException extends AuthException {

    public TokenRevocationException() {
        super(AuthErrorCode.UNAUTHORIZED_ACCESS);
    }

    public TokenRevocationException(String message) {
        super(AuthErrorCode.UNAUTHORIZED_ACCESS, message);
    }

}
