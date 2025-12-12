package com.lxp.enrollment.application.port.provided.dto.result;

import com.lxp.api.content.course.port.dto.result.CourseResult;

import java.util.List;

public record CourseSummaryResult(
        String courseId,
        String instructorId,
        String title,
        String thumbnailUrl,
        String description,
        Level difficulty,
        List<Long> tags
) {
    public static CourseSummaryResult from(CourseResult result) {
        return new CourseSummaryResult(
                result.courseUUID(),
                result.instructorUUID(),
                result.title(),
                result.thumbnailUrl(),
                result.description(),
                Level.valueOf(result.difficulty().name()),
                result.tags()
        );
    }
}
