package com.lxp.tag.domain.service;

import com.lxp.common.domain.annotation.DomainService;
import com.lxp.tag.domain.exception.TagErrorCode;
import com.lxp.tag.domain.exception.TagException;
import com.lxp.tag.domain.model.Tag;
import com.lxp.tag.domain.model.TagCategory;

@DomainService
public class ChangeCategoryDomainService {
    public void changeCategory(TagCategory category, Tag tag) {
        if (!category.isActive()) {
            throw new TagException(TagErrorCode.INVALID_ASSIGN_TAG);
        }

        tag.changeCategory(category.id());
    }
}
