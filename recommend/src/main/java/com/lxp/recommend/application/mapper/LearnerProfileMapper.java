package com.lxp.recommend.application.mapper;

import com.lxp.recommend.application.port.required.dto.LearnerProfileData;
import com.lxp.recommend.domain.dto.LearnerLevel;
import com.lxp.recommend.domain.dto.LearnerProfileView;

public class LearnerProfileMapper {

    /**
     * Port DTO → Domain VO
     */
    public static LearnerProfileView toDomain(LearnerProfileData data) {
        return new LearnerProfileView(
                data.learnerId(),
                data.interestTags(),                        // ← 2번째 (Set<String>)
                LearnerLevel.valueOf(data.learnerLevel())   // ← 3번째 (LearnerLevel)
        );
    }
}
