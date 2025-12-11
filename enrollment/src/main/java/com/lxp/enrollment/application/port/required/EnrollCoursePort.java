package com.lxp.enrollment.application.port.required;

import com.lxp.enrollment.domain.model.Enrollment;

public interface EnrollCoursePort {
    Enrollment save(Enrollment enrollment);
}
