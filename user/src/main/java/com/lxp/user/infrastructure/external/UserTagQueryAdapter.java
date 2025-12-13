package com.lxp.user.infrastructure.external;

import com.lxp.api.tag.port.external.TagCachePort;
import com.lxp.user.application.port.required.UserTagQueryPort;
import com.lxp.user.application.port.required.dto.TagResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserTagQueryAdapter implements UserTagQueryPort {

    private final TagCachePort tagCachePort;

    @Override
    public List<TagResult> findTagByIds(List<Long> tagId) {
        return tagCachePort.findByIds(tagId).stream()
            .map(it -> new TagResult(it.tagId(), it.name())).toList();
    }

}
