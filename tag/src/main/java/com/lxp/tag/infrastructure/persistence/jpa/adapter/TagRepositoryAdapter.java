package com.lxp.tag.infrastructure.persistence.jpa.adapter;

import com.lxp.tag.application.port.dto.TagView;
import com.lxp.tag.domain.model.Tag;
import com.lxp.tag.domain.repository.TagRepository;
import com.lxp.tag.infrastructure.persistence.jpa.TagCategoryJpaRepository;
import com.lxp.tag.infrastructure.persistence.jpa.TagJpaRepository;
import com.lxp.tag.infrastructure.persistence.jpa.entity.TagCategoryJpaEntity;
import com.lxp.tag.infrastructure.persistence.jpa.entity.TagJpaEntity;
import com.lxp.tag.infrastructure.persistence.jpa.enums.TagState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagRepositoryAdapter implements TagRepository {
    private final TagJpaRepository tagJpaRepository;
    private final TagCategoryJpaRepository tagCategoryJpaRepository;

    @Override
    public TagView save(Tag tag) {
        TagCategoryJpaEntity categoryJpaEntity = tagCategoryJpaRepository.findById(tag.tagCategoryId().value())
                .orElseThrow(() -> new IllegalStateException("Tag category not found with id: " + tag.tagCategoryId().value()));

        TagJpaEntity entity = new TagJpaEntity(
                tag.name(),
                tag.color(),
                tag.variant(),
                tag.isActive() ? TagState.ACTIVE : TagState.INACTIVE,
                categoryJpaEntity
        );

        TagJpaEntity saved = tagJpaRepository.save(entity);

        return TagJpaEntity.from(saved);

    }
}
