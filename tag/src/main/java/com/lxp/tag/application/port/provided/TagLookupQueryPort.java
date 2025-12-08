package com.lxp.tag.application.port.provided;

import com.lxp.tag.application.port.provided.dto.TagLookup;
import java.util.List;
import java.util.Optional;

public interface TagLookupQueryPort {
    List<TagLookup> findByIds(List<Long> ids);
    Optional<TagLookup> findByName(String name);
    Optional<TagLookup> findById(Long id);
    String findNameById(Long id);
    Long findIdByName(String name);
}
