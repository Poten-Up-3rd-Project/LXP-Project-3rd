package com.lxp.enrollment.application.service;

import com.lxp.api.enrollment.dto.query.GetEnrollmentHistoryQuery;
import com.lxp.enrollment.application.port.provided.dto.result.EnrollmentHistoryItem;
import com.lxp.common.domain.pagination.Page;
import com.lxp.common.domain.pagination.PageRequest;
import com.lxp.common.domain.pagination.Sort;
import com.lxp.enrollment.application.port.provided.dto.result.CourseSummaryResult;
import com.lxp.enrollment.application.port.provided.usecase.GetEnrollmentListUseCase;
import com.lxp.enrollment.application.port.required.TagQueryPort;
import com.lxp.enrollment.application.port.required.UserStatusQueryPort;
import com.lxp.enrollment.domain.exception.EnrollmentErrorCode;
import com.lxp.enrollment.domain.exception.EnrollmentException;
import com.lxp.enrollment.domain.model.CourseProgress;
import com.lxp.enrollment.domain.model.Enrollment;
import com.lxp.enrollment.domain.repository.CourseProgressRepository;
import com.lxp.enrollment.domain.repository.EnrollmentRepository;
import com.lxp.enrollment.infrastructure.external.adapter.CourseSummaryQueryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEnrollmentListService implements GetEnrollmentListUseCase {
    private final EnrollmentRepository enrollmentRepository;
    private final UserStatusQueryPort userStatusQueryPort;
    private final CourseSummaryQueryAdapter courseSummaryQueryAdapter;
    private final CourseProgressRepository courseProgressRepository;
    private final TagQueryPort tagQueryPort;

    @Override
    public Page<EnrollmentHistoryItem> getEnrollmentHistory(GetEnrollmentHistoryQuery query) {
        boolean isActiveUser = userStatusQueryPort.isActiveUser(query.userId());
        if (!isActiveUser) {
            throw new EnrollmentException(EnrollmentErrorCode.INVALID_USER);
        }
        Page<Enrollment> pageResult = enrollmentRepository.findByUserIdAndState(query.userId(), query.state(), PageRequest.of(
                query.page(),
                query.size(),
                Sort.by(Sort.Order.asc("createdAt"))
        ));

        List<String> courseIds = pageResult.content().stream().map(e -> e.courseId().value()).toList();
        List<CourseSummaryResult> courseResult = courseSummaryQueryAdapter.getCourseSummaryList(courseIds);

        List<EnrollmentHistoryItem> enrollmentHistoryItems = new ArrayList<>();
        for (Enrollment enrollment: pageResult.content()) {
            CourseSummaryResult courseSummaryResult = courseResult.stream()
                    .filter(c -> c.courseId().equals(enrollment.courseId()))
                    .findFirst().orElseThrow(() -> new EnrollmentException(EnrollmentErrorCode.ERROR_PARSING_ENROLLMENT));

            CourseProgress progress = courseProgressRepository.findByUserIdAndCourseId(enrollment.userId(), enrollment.courseId())
                    .orElseThrow(() -> new EnrollmentException(EnrollmentErrorCode.ERROR_PARSING_PROGRESS));

            EnrollmentHistoryItem item = new EnrollmentHistoryItem(
                    enrollment.enrollmentId().value(),
                    enrollment.state().toString(),
                    enrollment.courseId().value(),
                    courseSummaryResult.thumbnailUrl(),
                    progress.totalProgress(), //progress
                    courseSummaryResult.title(),
                    courseSummaryResult.description(),
                    courseSummaryResult.difficulty().toString(),
                    tagQueryPort.findByIds(courseSummaryResult.tags()),
                    enrollment.enrollmentDate().value()
            );
            enrollmentHistoryItems.add(item);
        }

        return Page.of(
                enrollmentHistoryItems,
                pageResult.pageNumber(),
                pageResult.pageSize(),
                pageResult.totalElements()
        );
    }
}
