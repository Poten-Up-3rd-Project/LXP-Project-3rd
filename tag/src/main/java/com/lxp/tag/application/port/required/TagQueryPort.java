package com.lxp.tag.application.port.required;

import com.lxp.tag.application.port.dto.TagView;

import java.util.List;
import java.util.Optional;

public interface TagQueryPort {
    List<TagView> findByIds(List<Long> ids);
    Optional<TagView> findById(Long id);
    Optional<TagView> findIdByName(String name);
}
