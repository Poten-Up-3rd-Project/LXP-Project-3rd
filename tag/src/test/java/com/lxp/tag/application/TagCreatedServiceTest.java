package com.lxp.tag.application;

import com.lxp.api.tag.port.dto.command.CreatedTagCommand;
import com.lxp.api.tag.port.dto.result.TagResult;
import com.lxp.common.event.integration.TagCreatedIntegrationEvent;
import com.lxp.tag.application.port.dto.TagView;
import com.lxp.tag.application.service.TagCreatedService;
import com.lxp.tag.domain.model.Tag;
import com.lxp.tag.domain.repository.TagRepository;
import com.lxp.tag.infrastructure.event.TagIntegrationEventPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagCreatedServiceTest {

    @Mock
    TagRepository tagRepository;

    @Mock
    TagIntegrationEventPublisher eventPublisher;

    @InjectMocks
    TagCreatedService service;

    @Test
    void handle_shouldSaveTag_andPublishIntegrationEvent() {
        // given
        CreatedTagCommand command = new CreatedTagCommand(
                1L, "Java", "ACTIVE", "#FF0000", "PRIMARY"
        );

        TagView savedTag = new TagView(
                10L, "Java", "#FF0000", "PRIMARY", "ACTIVE"
        );

        when(tagRepository.save(any(Tag.class)))
                .thenReturn(savedTag);

        // when
        TagResult result = service.handle(command);

        // then
        verify(tagRepository).save(any(Tag.class));
        verify(eventPublisher).publish(any(TagCreatedIntegrationEvent.class));

        assertThat(result.tagId()).isEqualTo(10L);
        assertThat(result.name()).isEqualTo("Java");
    }
}