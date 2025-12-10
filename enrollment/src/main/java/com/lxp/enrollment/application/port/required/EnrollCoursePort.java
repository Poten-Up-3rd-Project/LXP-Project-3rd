package com.lxp.enrollment.application.port.required;

import com.lxp.enrollment.domain.model.Enrollment;

public interface EnrollCoursePort {
    void save(Enrollment enrollment);
}
