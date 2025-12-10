package com.lxp.tag.application.port.dto;

import com.lxp.api.tag.port.dto.result.TagResult;

public record TagView(
        long tagId,
        String name,
        String state,
        String color,
        String variant
) {
    public static TagResult from(TagView view) {
        return new TagResult(
                view.tagId,
                view.name,
                view.state,
                view.color,
                view.variant
        );
    }
}