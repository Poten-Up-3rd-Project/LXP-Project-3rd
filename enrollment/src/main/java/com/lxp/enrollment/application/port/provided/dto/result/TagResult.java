package com.lxp.enrollment.application.port.provided.dto.result;

public record TagResult(
        long tagId,
        String name,
        String state, // ACTIVE, INACTIVE
        String color,
        String variant
) {
    public static TagResult from(com.lxp.api.tag.port.dto.result.TagResult tag) {
        return new TagResult(
                tag.tagId(),
                tag.name(),
                tag.state(),
                tag.color(),
                tag.variant()
        );
    }
}
