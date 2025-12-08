package com.lxp.user.application.user.command;

import java.util.UUID;

public record UserSaveCommand(UUID userId, String name, String email, String role) {
}
