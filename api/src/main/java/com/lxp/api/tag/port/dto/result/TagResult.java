package com.lxp.api.tag.port.dto.result;

public record TagResult(
        long tagId,
        String name,
        String state, // ACTIVE, INACTIVE
        String color,
        String variant
) {
}
