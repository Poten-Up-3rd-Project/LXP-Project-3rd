package com.lxp.tag.application.service;

import com.lxp.tag.application.port.provided.dto.TagLookup;
import com.lxp.tag.application.port.required.query.TagLookupStore;
import com.lxp.tag.domain.model.vo.TagId;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisTagLookupStore implements TagLookupStore {
    private final RedisTemplate<String, String> redisTemplate;


    private String idKey(TagId id) {
        return "tag:id:" + id.value();
    }

    private String nameKey(String name) {
        return "tag:name" + name.toLowerCase();
    }

    @Override
    public void save(TagLookup snapshot) {
        String idKey = idKey(new TagId(snapshot.tagId()));
        String nameKey = nameKey(snapshot.name());

//        String json = objectMapper.writeValueAsString(snapshot);
//        redisTemplate.opsForValue().set(idKey, json);
        redisTemplate.opsForValue().set(nameKey, snapshot.tagId().toString());
    }

    @Override
    public void saveAll(List<TagLookup> snapshots) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<TagLookup> findByIds(List<Long> ids) {
        return null;
    }

    @Override
    public Optional<TagLookup> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<TagLookup> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public String findNameById(Long id) {
        return null;
    }

    @Override
    public Long findIdByName(String name) {
        return null;
    }
}
