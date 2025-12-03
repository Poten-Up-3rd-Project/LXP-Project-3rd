package com.lxp.auth.application.local.command;

public record HandleUserRegisterCommand(String email, String password, String role) {
}
