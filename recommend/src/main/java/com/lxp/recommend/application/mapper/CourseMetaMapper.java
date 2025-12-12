package com.lxp.recommend.application.mapper;

import com.lxp.recommend.application.port.required.dto.CourseMetaData;
import com.lxp.recommend.domain.dto.DifficultyLevel;
import com.lxp.recommend.domain.model.CourseCandidate;
import com.lxp.recommend.domain.model.ids.CourseId;

public class CourseMetaMapper {

    public static CourseCandidate toDomain(CourseMetaData data) {
        return new CourseCandidate(
                CourseId.of(data.courseId()),
                data.tags(),
                DifficultyLevel.valueOf(data.difficulty()),
                data.isPublic()
        );
    }
}
