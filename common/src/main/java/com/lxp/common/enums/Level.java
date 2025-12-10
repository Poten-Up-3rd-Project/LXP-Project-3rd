package com.lxp.common.enums;

public enum Level {
    JUNIOR("주니어"),
    MIDDLE("미들"),
    SENIOR("시니어"),
    EXPERT("익스퍼트");

    private final String description;

    Level(String description) {
        this.description = description;
    }
}
