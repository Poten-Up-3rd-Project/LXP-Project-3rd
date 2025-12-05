package com.lxp.auth.application.local.port.required.command;

public record HandleUserRegisterCommand(String email, String password, String name, String role) {
}
