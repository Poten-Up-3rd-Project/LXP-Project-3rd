package com.lxp.tag.application.port.provided.usecase;

import com.lxp.tag.application.port.provided.dto.CategoryResult;

import java.util.List;

public interface FindCategoryUseCase {
    List<CategoryResult> findAll();
}
