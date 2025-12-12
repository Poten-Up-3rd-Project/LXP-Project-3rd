package com.lxp.enrollment.interfaces.dto.response;

public record TagResponse(
        long tagId,
        String content,
        String color,
        String variant
) {
}
