package com.lxp.api.tag.port.usecase;

import com.lxp.api.tag.port.dto.result.CategoryResult;

import java.util.List;

public interface FindCategoryUseCase {
    List<CategoryResult> findAll();
}
