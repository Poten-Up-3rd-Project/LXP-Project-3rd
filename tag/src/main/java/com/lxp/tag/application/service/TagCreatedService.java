package com.lxp.tag.application.service;

import com.lxp.api.tag.port.dto.command.CreatedTagCommand;
import com.lxp.api.tag.port.dto.result.TagResult;
import com.lxp.tag.application.port.provided.usecase.CreatedTagUseCase;
import com.lxp.common.event.integration.TagCreatedIntegrationEvent;
import com.lxp.tag.application.port.dto.TagView;
import com.lxp.tag.domain.model.Tag;
import com.lxp.tag.domain.model.vo.TagCategoryId;
import com.lxp.tag.domain.repository.TagRepository;
import com.lxp.tag.infrastructure.event.TagIntegrationEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagCreatedService implements CreatedTagUseCase {
    private final TagRepository tagRepository;
    private final TagIntegrationEventPublisher eventPublisher;

    @Override
    public TagResult handle(CreatedTagCommand command) {
        Tag tag =  Tag.create(
                new TagCategoryId(command.tagCategoryId()),
                command.name(),
                command.color(),
                command.variant()
        );

        TagView savedTag = tagRepository.save(tag);

        // uuid 존재하지 않아 integration으로 바로 발행
        // 추후 domain event로 변경 (같은 BC에서 발행하는 것이므로)
        eventPublisher.publish(TagCreatedIntegrationEvent.of(
                savedTag.tagId(),
                savedTag.name(),
                savedTag.color(),
                savedTag.variant(),
                savedTag.state()
        ));
        return TagView.from(savedTag);
    }
}
