package com.lxp.auth.presentation.rest.local.dto.reqeust;

import com.lxp.auth.application.local.command.HandleUserRegisterCommand;

public record RegisterRequest(String email, String password, String role) {

    public HandleUserRegisterCommand toCommand() {
        return new HandleUserRegisterCommand(this.email, this.password, this.role);
    }
}
