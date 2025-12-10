package com.lxp.api.enrollment.dto.command;

public record EnrollCourseCommand(
    String userId,
    String courseId
) {

}
