package com.lxp.auth.domain.common.model.vo;

public record AuthTokenInfo(String accessToken, long expiresIn) {
}
