package com.lxp.auth.presentation.rest.local.dto.reqeust;

import com.lxp.auth.application.local.port.provided.command.HandleLoginCommand;

public record LoginRequest(String email, String password) {

    public HandleLoginCommand toCommand() {
        return new HandleLoginCommand(this.email, this.password);
    }
}
