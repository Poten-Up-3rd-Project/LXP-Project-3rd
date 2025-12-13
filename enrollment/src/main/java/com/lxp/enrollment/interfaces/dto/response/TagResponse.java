package com.lxp.enrollment.interfaces.dto.response;

import com.lxp.enrollment.application.port.provided.dto.result.TagResult;

public record TagResponse(
        long tagId,
        String content,
        String color,
        String variant
) {
    public static TagResponse from(TagResult result) {
        return new TagResponse(
                result.tagId(),
                result.name(),
                result.color(),
                result.variant()
        );
    }
}
