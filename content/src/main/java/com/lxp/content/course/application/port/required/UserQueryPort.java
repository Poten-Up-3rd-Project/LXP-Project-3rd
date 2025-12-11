package com.lxp.content.course.application.port.required;

import com.lxp.content.course.application.port.required.dto.InstructorInfo;

public interface UserQueryPort {
    InstructorInfo getInstructorInfo(String userId);
}
