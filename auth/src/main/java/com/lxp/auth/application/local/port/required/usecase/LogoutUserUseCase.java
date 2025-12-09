package com.lxp.auth.application.local.port.required.usecase;

@FunctionalInterface
public interface LogoutUserUseCase {

    void logout(String accessToken, int remainingSeconds);

}
