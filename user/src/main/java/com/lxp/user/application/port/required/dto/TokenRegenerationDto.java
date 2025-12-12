package com.lxp.user.application.port.required.dto;

public record TokenRegenerationDto(String userId, String email, String role, String token) {
}
