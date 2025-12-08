package com.lxp.tag.application.port.required.query;

import com.lxp.tag.application.port.provided.dto.TagLookup;
import java.util.List;
import java.util.Optional;

public interface TagLookupStore {
    void save(TagLookup snapshot);
    void saveAll(List<TagLookup> snapshots);
    void delete(long id);
    List<TagLookup> findByIds(List<Long> ids);
    Optional<TagLookup> findByName(String name);
    Optional<TagLookup> findById(Long id);
    String findNameById(Long id);
    Long findIdByName(String name);
}
