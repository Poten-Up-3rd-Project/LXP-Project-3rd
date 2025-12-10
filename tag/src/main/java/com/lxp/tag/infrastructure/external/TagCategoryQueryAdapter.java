package com.lxp.tag.infrastructure.external;

import com.lxp.api.tag.port.dto.result.CategoryResult;
import com.lxp.tag.application.port.required.TagCategoryQueryPort;
import com.lxp.tag.infrastructure.persistence.jpa.TagCategoryJpaRepository;
import com.lxp.tag.infrastructure.persistence.jpa.entity.TagCategoryJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TagCategoryQueryAdapter implements TagCategoryQueryPort {
    private final TagCategoryJpaRepository tagCategoryJpaRepository;

    @Override
    public List<CategoryResult> findAllWithTags() {
//        return tagCategoryJpaRepository.findAllWithTags()
//                .stream().map(TagCategoryJpaEntity::toResult)
//                .toList();

        return List.of();
    }
}
