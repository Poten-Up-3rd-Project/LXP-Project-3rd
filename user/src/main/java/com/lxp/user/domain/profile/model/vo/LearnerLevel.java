package com.lxp.user.domain.profile.model.vo;

public enum LearnerLevel {
    BEGINNER("초급"),
    INTERMEDIATE("중급"),
    ADVANCED("고급");

    private final String description;

    LearnerLevel(String description) {
        this.description = description;
    }
}
