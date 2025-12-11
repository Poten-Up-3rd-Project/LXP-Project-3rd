package com.lxp.tag.infrastructure.persistence.jpa.adapter;

import com.lxp.tag.application.port.dto.CategoryView;
import com.lxp.tag.application.port.required.TagCategoryQueryPort;
import com.lxp.tag.infrastructure.persistence.jpa.TagCategoryJpaRepository;
import com.lxp.tag.infrastructure.persistence.jpa.entity.TagCategoryJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TagCategoryQueryAdapter implements TagCategoryQueryPort {
    private final TagCategoryJpaRepository tagCategoryJpaRepository;

    @Override
    public List<CategoryView> findAllWithTags() {
        return tagCategoryJpaRepository.findAllWithTags()
                .stream().map(TagCategoryJpaEntity::from)
                .toList();
    }

    @Override
    public Optional<CategoryView> findById(Long id) {
        return tagCategoryJpaRepository.findById(id).map(TagCategoryJpaEntity::from);
    }
}
