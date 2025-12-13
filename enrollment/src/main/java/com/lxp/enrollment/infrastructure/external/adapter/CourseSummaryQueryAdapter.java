package com.lxp.enrollment.infrastructure.external.adapter;

import com.lxp.api.content.course.port.external.ExternalCourseSummaryPort;
import com.lxp.api.content.course.port.external.dto.result.CourseResult;
import com.lxp.api.user.port.dto.result.UserInfoResponse;
import com.lxp.api.user.port.external.ExternalUserInfoPort;
import com.lxp.enrollment.application.port.provided.dto.result.CourseSummaryResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseSummaryQueryAdapter {
    private final ExternalCourseSummaryPort externalCourseSummaryPort;
    private final ExternalUserInfoPort externalUserInfoPort;

    public List<CourseSummaryResult> getCourseSummaryList(Collection<String> courseIds) {
        List<CourseResult> results =  externalCourseSummaryPort.getCourseSummaryList(courseIds);

        List<CourseSummaryResult> courseSummaryResults = new ArrayList<>();
        for (CourseResult result : results) {
            String instructorId = result.instructorUUID();
            UserInfoResponse response = externalUserInfoPort.userInfo(instructorId).orElse(null);
            CourseSummaryResult courseSummaryResult = CourseSummaryResult.from(result, response.name());
            courseSummaryResults.add(courseSummaryResult);
        }

        return courseSummaryResults;
    }
}
