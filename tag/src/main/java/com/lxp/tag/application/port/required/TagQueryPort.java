package com.lxp.tag.application.port.required;

import com.lxp.tag.application.port.provided.dto.result.TagResult;

import java.util.List;
import java.util.Optional;

public interface TagQueryPort {
    List<TagResult> findByIds(List<Long> ids);
    Optional<TagResult> findById(Long id);
    Optional<TagResult> findIdByName(String name);
}
