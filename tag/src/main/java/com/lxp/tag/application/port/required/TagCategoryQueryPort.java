package com.lxp.tag.application.port.required;

import com.lxp.tag.application.port.provided.dto.result.CategoryResult;

import java.util.List;

public interface TagCategoryQueryPort {
    List<CategoryResult> findAllWithTags();
}
