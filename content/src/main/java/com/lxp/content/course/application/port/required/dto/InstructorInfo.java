package com.lxp.content.course.application.port.required.dto;

public record InstructorInfo(
    String userId,
    String name,
    String role,
    String status
) {
}
