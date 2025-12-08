package com.lxp.auth.application.local.port.required.command;

public record HandleUserInfoCommand(String userId, String email, String role) {
}
