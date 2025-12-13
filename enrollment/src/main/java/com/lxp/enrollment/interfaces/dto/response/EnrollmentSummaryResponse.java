package com.lxp.enrollment.interfaces.dto.response;

import com.lxp.enrollment.application.port.provided.dto.result.EnrollmentHistoryItem;

import java.util.List;

public record EnrollmentSummaryResponse(
        long enrollmentId,
        String state, // ENROLLED, COMPLETED, CANCELLED
        String courseId,
        String thumbnailUrl,
        float totalProgress,
        String courseTitle,
        String courseDescription,
        String instructorName,
        String level, // JUNIOR, MIDDLE, SENIOR, EXPERT
        List<TagResponse> tags
) {
    public static EnrollmentSummaryResponse from(EnrollmentHistoryItem item) {
        return new EnrollmentSummaryResponse(
                item.enrollmentId(),
                item.state(),
                item.courseId(),
                item.thumbnailUrl(),
                item.totalProgress(),
                item.courseTitle(),
                item.courseDescription(),
                item.instructorName(),
                item.level(),
                item.tags().stream().map(TagResponse::from).toList()
        );
    }
}
