package com.lxp.auth.application.local.port.required.command;

import java.util.List;
import java.util.UUID;

public record HandleUserRegisterCommand(
    UUID userId,
    String email,
    String password,
    String name,
    String role,
    List<Long> tags,
    String learnerLevel,
    String job
) {
}
