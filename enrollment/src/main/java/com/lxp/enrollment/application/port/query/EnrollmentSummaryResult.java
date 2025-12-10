package com.lxp.enrollment.application.port.query;

import com.lxp.content.course.application.port.provided.dto.result.CourseResult;

import java.util.List;

public record EnrollmentSummaryResult(
        long enrollmentId,
        String state, // ENROLLED, COMPLETED, CANCELED
        String userUUID,
        String courseUUID,
        long courseId,
        String instructorUUID,
        String title,
        String thumbnailUrl,
        String description,
        String difficulty,
        List<Long> tags
) {
    public static EnrollmentSummaryResult of(EnrollmentResult enrollment, CourseResult course) {
        return null;
    }
}