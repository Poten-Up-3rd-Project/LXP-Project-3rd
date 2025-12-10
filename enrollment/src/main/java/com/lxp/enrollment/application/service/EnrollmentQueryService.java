package com.lxp.enrollment.application.service;

import com.lxp.common.domain.pagination.Page;
import com.lxp.content.course.application.port.provided.dto.result.CourseResult;
import com.lxp.content.course.application.port.provided.external.ExternalCourseSummaryPort;
import com.lxp.enrollment.application.port.query.EnrollmentResult;
import com.lxp.enrollment.application.port.query.EnrollmentSummaryResult;
import com.lxp.enrollment.application.port.required.EnrollmentQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentQueryService {
    private final EnrollmentQueryPort enrollmentQueryPort;
    private final ExternalCourseSummaryPort courseSummaryPort;

    public Page<EnrollmentSummaryResult> findEnrollments(String userId, String state, int page, int size) {
        Page<EnrollmentResult> enrollmentPage = enrollmentQueryPort.findEnrollments(userId, state, page, size);

        List<EnrollmentSummaryResult> summaryResults = new ArrayList<>();
        for (EnrollmentResult result : enrollmentPage.content()) {
            String courseUUID = result.courseUUID();
            CourseResult courseResult = courseSummaryPort.getCourseSummary(courseUUID)
                    .orElseThrow(() -> new IllegalStateException("course 없음"));
            EnrollmentSummaryResult summaryResult = EnrollmentSummaryResult.of(result, courseResult);
            summaryResults.add(summaryResult);
        }

        return Page.of(summaryResults, enrollmentPage.pageNumber(), enrollmentPage.pageSize(), enrollmentPage.totalElements());
    }
}
