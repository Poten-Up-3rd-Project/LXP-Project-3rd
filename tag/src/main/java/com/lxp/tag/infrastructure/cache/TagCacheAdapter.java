package com.lxp.tag.infrastructure.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lxp.api.tag.port.external.TagCachePort;
import com.lxp.api.tag.port.dto.result.TagResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class TagCacheAdapter implements TagCachePort {
    private final ObjectMapper objectMapper;
    private final RedisTemplate<String, String> redisTemplate;
    private static final String TAG_ID_KEY = "tag:id:";       // tag:id:{id} → JSON
    private static final String TAG_NAME_KEY = "tag:name:";   // tag:name:{name} → id
    private static final long TTL_HOURS = 24;

    @Override
    public List<TagResult> findAll() {
        return List.of();
    }

    @Override
    public Optional<TagResult> findById(Long id) {
        String key = TAG_ID_KEY + id;
        String json = redisTemplate.opsForValue().get(key);

        if (json == null) {
            return Optional.empty();
        }

        try {
            return Optional.of(objectMapper.readValue(json, TagResult.class));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> findByName(String name) {
        String key = TAG_NAME_KEY + name.toLowerCase();
        String value = redisTemplate.opsForValue().get(key);
        return Optional.ofNullable(value).map(Long::parseLong);
    }

    @Override
    public List<TagResult> findByIds(Set<Long> ids) {
        return List.of();
    }

    @Override
    public void save(TagResult tag) {
        try {
            String json = objectMapper.writeValueAsString(tag);

            // id → 전체 데이터
            redisTemplate.opsForValue().set(
                    TAG_ID_KEY + tag.tagId(),
                    json,
                    TTL_HOURS,
                    TimeUnit.HOURS
            );

            // name → id (역인덱스)
            redisTemplate.opsForValue().set(
                    TAG_NAME_KEY + tag.name().toLowerCase(),
                    String.valueOf(tag.tagId()),
                    TTL_HOURS,
                    TimeUnit.HOURS
            );

        } catch (JsonProcessingException e) {
            //log.error("[TagCache] 직렬화 실패 - id={}", data.id(), e);
        }
    }

    @Override
    public void refreshAll(List<TagResult> tags) {

    }

    @Override
    public void evictAll() {

    }
}
