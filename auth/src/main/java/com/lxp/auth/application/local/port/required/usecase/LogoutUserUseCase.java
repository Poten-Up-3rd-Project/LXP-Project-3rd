package com.lxp.auth.application.local.port.required.usecase;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface LogoutUserUseCase {

    void logout(HttpServletRequest request);

}
