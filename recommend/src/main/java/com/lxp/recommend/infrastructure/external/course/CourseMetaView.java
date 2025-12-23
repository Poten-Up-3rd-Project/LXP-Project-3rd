package com.lxp.recommend.infrastructure.external.course;

import java.util.Set;

public record CourseMetaView(
        String courseId,          // UUID -> Long
        Set<String> tags,
        DifficultyLevel difficulty,
        boolean isPublic
) {}