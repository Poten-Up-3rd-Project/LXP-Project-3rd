package com.lxp.tag.application.port.required;


import com.lxp.api.tag.port.dto.result.CategoryResult;

import java.util.List;

public interface TagCategoryQueryPort {
    List<CategoryResult> findAllWithTags();
}
