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

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetEnrollmentListService implements GetEnrollmentListUseCase {
    private final EnrollmentRepository enrollmentRepository;
    private final UserStatusQueryPort userStatusQueryPort;
    private final CourseSummaryQueryAdapter courseSummaryQueryAdapter;
    // TODO:
    // private final CourseProgressRepository courseProgressRepository;
    private final TagQueryPort tagQueryPort;

    @Override
    public Page<EnrollmentHistoryItem> getEnrollmentHistory(GetEnrollmentHistoryQuery query) {
        validateActiveUser(query.userId());

        Page<Enrollment> pageResult = enrollmentRepository.findByUserIdAndState(
                query.userId(),
                query.state(),
                createPageRequest(query)
        );

        List<String> courseIds = pageResult.content().stream()
                .map(enrollment -> enrollment.courseId().value())
                .distinct()
                .toList();

        Map<String, CourseSummaryResult> courseSummaryById = courseSummaryQueryAdapter.getCourseSummaryList(courseIds)
                .stream()
                .collect(Collectors.toMap(CourseSummaryResult::courseId, Function.identity(), (a, b) -> a));

        List<EnrollmentHistoryItem> enrollmentHistoryItems = pageResult.content().stream()
                .map(enrollment -> toEnrollmentHistoryItem(enrollment, courseSummaryById))
                .toList();

        return Page.of(enrollmentHistoryItems, pageResult.pageNumber(), pageResult.pageSize(), pageResult.totalElements());
    }

    private void validateActiveUser(String userId) {
        if (!userStatusQueryPort.isActiveUser(userId)) {
            throw new EnrollmentException(EnrollmentErrorCode.INVALID_USER);
        }
    }

    private PageRequest createPageRequest(GetEnrollmentHistoryQuery query) {
        Sort.Order order = isLatestSort(query.sort())
                ? Sort.Order.desc("createdAt")
                : Sort.Order.asc("createdAt");

        return PageRequest.of(query.page(), query.size(), Sort.by(order));
    }

    private boolean isLatestSort(String sort) {
        return sort == null || sort.equalsIgnoreCase("LATEST");
    }

    private EnrollmentHistoryItem toEnrollmentHistoryItem(
            Enrollment enrollment,
            Map<String, CourseSummaryResult> courseSummaryById
    ) {
        CourseSummaryResult courseSummaryResult = courseSummaryById.get(enrollment.courseId().value());
        if (courseSummaryResult == null) {
            throw new EnrollmentException(EnrollmentErrorCode.ERROR_PARSING_ENROLLMENT);
        }

//        CourseProgress progress = courseProgressRepository.findByUserIdAndCourseId(enrollment.userId(), enrollment.courseId())
//                .orElseThrow(() -> new EnrollmentException(EnrollmentErrorCode.ERROR_PARSING_PROGRESS));

        return new EnrollmentHistoryItem(
                enrollment.enrollmentId().value(),
                enrollment.state().toString(),
                enrollment.courseId().value(),
                courseSummaryResult.thumbnailUrl(),
                0, //progress.totalProgress(),
                courseSummaryResult.title(),
                courseSummaryResult.description(),
                courseSummaryResult.instructorName(),
                courseSummaryResult.difficulty().toString(),
                tagQueryPort.findByIds(courseSummaryResult.tags()),
                enrollment.enrollmentDate().value()
        );
    }
}
