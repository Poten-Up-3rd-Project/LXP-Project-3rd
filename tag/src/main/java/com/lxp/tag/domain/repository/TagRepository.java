package com.lxp.tag.domain.repository;

import com.lxp.tag.application.port.dto.TagView;
import com.lxp.tag.domain.model.Tag;

public interface TagRepository {
    TagView save(Tag tag);
}
