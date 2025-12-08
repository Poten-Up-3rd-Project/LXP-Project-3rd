package com.lxp.tag.infrastructure.adapter;

import com.lxp.tag.application.port.provided.dto.TagLookup;
import com.lxp.tag.application.port.required.query.TagLookupStore;
import com.lxp.tag.infrastructure.persistence.entity.TagJpaEntity;
import com.lxp.tag.infrastructure.persistence.entity.enums.TagState;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagLookupQueryAdapter implements TagLookupStore {
    private final RedisTemplate<String, TagLookup> tagLookupRedisTemplate;
    private final RedisTemplate<String, Long> longRedisTemplate;

    private String keyById(Long id) { return "tag:id" + id; }

    private String keyByName(String name) { return "tag:name:" + name; }

    private TagLookup toLookup(TagJpaEntity entity) {
        return new TagLookup(
            entity.getId(),
            entity.getName(),
            entity.getState() == TagState.ACTIVE
        );
    }

    private void cache(TagLookup lookup) {
        String idKey = keyById(lookup.tagId());
        String nameKey = keyByName(lookup.name());

        // 캐시 저장
        tagLookupRedisTemplate.opsForValue().set(idKey, lookup);
        longRedisTemplate.opsForValue().set(nameKey, lookup.tagId());
    }

    @Override
    public void save(TagLookup snapshot) {

    }

    @Override
    public void saveAll(List<TagLookup> snapshots) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<TagLookup> findByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        List<String> keys = ids.stream().map(this::keyById).toList();
        List<TagLookup> cached = tagLookupRedisTemplate.opsForValue().multiGet(keys);

        Map<Long, TagLookup> resultMap = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            TagLookup lookup = cached.get(i);
            if (lookup != null) {
                resultMap.put(ids.get(i), lookup);
            }
        }

        return ids.stream()
            .map(resultMap::get)
            .filter(Objects::nonNull)
            .toList();
    }

    @Override
    public Optional<TagLookup> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<TagLookup> findById(Long id) {
        String key = keyById(id);
        TagLookup cached = tagLookupRedisTemplate.opsForValue().get(key);
        if (cached != null) {
            return Optional.of(cached);
        }
        return Optional.empty();
    }

    @Override
    public String findNameById(Long id) {
        return null;
    }

    @Override
    public Long findIdByName(String name) {
        String nameKey = keyByName(name);
        return longRedisTemplate.opsForValue().get(nameKey);
    }
}
