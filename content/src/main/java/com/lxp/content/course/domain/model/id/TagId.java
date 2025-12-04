package com.lxp.content.course.domain.model.id;

public record TagId(String value) {
    public TagId {
        if (value != null && value.isBlank()) {
            throw new IllegalArgumentException("TagId must be a positive number.");
        }
    }
}
