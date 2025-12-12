package com.lxp.enrollment.infrastructure.external.adapter;

import com.lxp.api.tag.port.external.ExternalFindTagPort;
import com.lxp.enrollment.application.port.provided.dto.result.TagResult;
import com.lxp.enrollment.application.port.required.TagQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EnrollmentTagQueryAdapter implements TagQueryPort {
    private final ExternalFindTagPort externalFindTagPort;

    @Override
    public List<TagResult> findByIds(List<Long> ids) {
        return externalFindTagPort.findByIds(ids)
                .stream().map(TagResult::from)
                .toList();
    }
}
