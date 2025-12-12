package com.lxp.enrollment.infrastructure.external.adapter;

import com.lxp.api.content.course.port.external.ExternalCourseSummaryPort;
import com.lxp.enrollment.application.port.provided.dto.result.CourseSummaryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseSummaryQueryAdapter {
    private final ExternalCourseSummaryPort externalCourseSummaryPort;

    public List<CourseSummaryResult> getCourseSummaryList(Collection<String> courseIds) {
        return externalCourseSummaryPort.getCourseSummaryList(courseIds)
                .stream().map(CourseSummaryResult::from)
                .toList();
    }
}
