package com.lxp.tag.application.port.required;

import com.lxp.tag.application.port.dto.CategoryView;

import java.util.List;

public interface TagCategoryQueryPort {
    List<CategoryView> findAllWithTags();
}
