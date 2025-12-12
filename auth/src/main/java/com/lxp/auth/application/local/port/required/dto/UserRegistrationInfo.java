package com.lxp.auth.application.local.port.required.dto;

import java.util.List;
import java.util.UUID;

public record UserRegistrationInfo(
    UUID userId,
    String name,
    String email,
    String role,

    List<Long> tags,
    String level,
    String job
) {
}
