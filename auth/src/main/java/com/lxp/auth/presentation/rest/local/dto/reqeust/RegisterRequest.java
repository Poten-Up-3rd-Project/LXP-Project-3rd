package com.lxp.auth.presentation.rest.local.dto.reqeust;

import com.lxp.auth.application.local.port.required.command.HandleRegisterAuthCommand;

import java.util.List;

public record RegisterRequest(
    String email,
    String password,
    String name,
    String role,
    List<Long> tags,
    String learnerLevel,
    String job
) {

    public HandleRegisterAuthCommand toCommand() {
        return new HandleRegisterAuthCommand(
            this.email,
            this.password,
            this.name,
            this.role,
            this.tags,
            this.learnerLevel,
            this.job
        );
    }
}
