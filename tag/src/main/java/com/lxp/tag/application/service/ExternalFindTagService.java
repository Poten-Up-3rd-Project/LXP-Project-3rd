package com.lxp.tag.application.service;

import com.lxp.api.tag.port.dto.result.TagResult;
import com.lxp.api.tag.port.external.ExternalFindTagPort;
import com.lxp.tag.application.port.dto.TagView;
import com.lxp.tag.infrastructure.persistence.jpa.adapter.TagQueryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExternalFindTagService implements ExternalFindTagPort {
    private final TagQueryAdapter tagQueryAdapter;

    @Override
    public List<TagResult> findByIds(List<Long> ids) {
        return tagQueryAdapter.findByIds(ids)
                .stream().map(TagView::from)
                .toList();
    }

    @Override
    public Optional<TagResult> findById(Long id) {
        return tagQueryAdapter.findById(id).map(TagView::from);
    }

    @Override
    public Optional<TagResult> findIdByName(String name) {
        return tagQueryAdapter.findIdByName(name).map(TagView::from);
    }
}
