package com.lxp.enrollment.domain.model.vo;

public record UserId(String value) {
    public UserId {
        if (value != null && value.isEmpty()) {
            throw new IllegalArgumentException("UserId must be not empty");
        }
    }
}
