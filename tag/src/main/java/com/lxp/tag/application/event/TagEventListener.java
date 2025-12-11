package com.lxp.tag.application.event;

import com.lxp.api.tag.port.external.TagCachePort;
import com.lxp.api.tag.port.dto.result.TagResult;
import com.lxp.common.event.integration.TagCreatedIntegrationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagEventListener {
    private final TagCachePort tagCachePort;
    @EventListener
    public void on(TagCreatedIntegrationEvent event) {
       // log.info("[TagCache] 생성 - id={}, name={}", event.tagId(), event.name());

        tagCachePort.save(new TagResult(
                event.tagId(),
                event.name(),
                event.color(),
                event.variant(),
                event.state()
        ));
    }}
