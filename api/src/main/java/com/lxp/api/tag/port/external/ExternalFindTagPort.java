package com.lxp.api.tag.port.external;

import com.lxp.api.tag.port.dto.result.TagResult;

import java.util.List;
import java.util.Optional;

public interface ExternalFindTagPort {
    List<TagResult> findByIds(List<Long> ids);
    Optional<TagResult> findById(Long id);
    Optional<TagResult> findIdByName(String name);
}
