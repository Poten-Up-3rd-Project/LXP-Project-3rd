package com.lxp.tag.infrastructure.persistence.jpa.adapter;

import com.lxp.tag.application.port.dto.TagView;
import com.lxp.tag.application.port.required.TagQueryPort;
import com.lxp.tag.infrastructure.persistence.jpa.TagJpaRepository;
import com.lxp.tag.infrastructure.persistence.jpa.entity.TagJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TagQueryAdapter implements TagQueryPort {
    private final TagJpaRepository tagJpaRepository;

    @Override
    public List<TagView> findByIds(List<Long> ids) {
        return tagJpaRepository.findByIdIn(ids)
                .stream().map(TagJpaEntity::from)
                .toList();
    }

    @Override
    public Optional<TagView> findById(Long id) {
        return tagJpaRepository.findById(id).map(TagJpaEntity::from);
    }

    @Override
    public Optional<TagView> findIdByName(String name) {
        return tagJpaRepository.findByName(name).map(TagJpaEntity::from);
    }
}
