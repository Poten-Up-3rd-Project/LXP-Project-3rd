package com.lxp.user.application.port.required.dto;

public record AuthTokenResult(String accessToken, long expiresIn) {
}
