package com.lxp.tag.application.port.required;

import com.lxp.tag.application.port.dto.CategoryView;

import java.util.List;
import java.util.Optional;

public interface TagCategoryQueryPort {
    List<CategoryView> findAllWithTags();
    Optional<CategoryView> findById(Long id);
}
