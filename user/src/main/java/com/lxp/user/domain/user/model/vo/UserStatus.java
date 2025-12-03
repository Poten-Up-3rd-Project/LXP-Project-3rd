package com.lxp.user.domain.user.model.vo;


public enum UserStatus {
    ACTIVE,
    INACTIVE,
    DELETED;

    public boolean catTransitionTo(UserStatus other) {
        return switch (this) {
            case ACTIVE -> other == ACTIVE || other == DELETED;
            case INACTIVE -> other == INACTIVE || other == DELETED;
            case DELETED -> false;
        };
    }

    public boolean isActive() {
        return this == ACTIVE;
    }
}

