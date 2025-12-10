package com.lxp.tag.application.port.dto;

import com.lxp.api.tag.port.dto.result.CategoryResult;

import java.util.List;

public record CategoryView(
        long tagCategoryId,
        String name,
        String state,
        List<TagView> tags
) {
    public static CategoryResult from(CategoryView view) {
        return new CategoryResult(
                view.tagCategoryId(),
                view.name,
                view.state,
                view.tags.stream().map(TagView::from).toList()
        );
    }
}
