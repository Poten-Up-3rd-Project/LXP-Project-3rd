package com.lxp.tag.domain.service;

import com.lxp.common.domain.annotation.DomainService;
import com.lxp.tag.domain.exception.TagErrorCode;
import com.lxp.tag.domain.exception.TagException;
import com.lxp.tag.domain.model.Tag;
import com.lxp.tag.domain.model.TagCategory;

@DomainService
public class CreateTagDomainService {
    public Tag create(TagCategory category, String name) {
        if (!category.isActive()) {
            throw new TagException(TagErrorCode.FAIL_CREATE_TAG_IN_INVALID_CATEGORY);
        }

        return Tag.create(category.id(), name);
    }
}
