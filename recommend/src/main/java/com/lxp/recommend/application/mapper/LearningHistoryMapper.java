package com.lxp.recommend.application.mapper;

import com.lxp.recommend.application.port.required.dto.LearningHistoryData;
import com.lxp.recommend.domain.dto.EnrollmentStatus;
import com.lxp.recommend.domain.dto.LearningStatusView;

public class LearningHistoryMapper {

    public static LearningStatusView toDomain(LearningHistoryData data) {
        return new LearningStatusView(
                data.learnerId(),
                data.courseId(),
                EnrollmentStatus.valueOf(data.status())
        );
    }
}
