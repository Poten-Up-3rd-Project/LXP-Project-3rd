package com.lxp.enrollment.application.port.required;

import com.lxp.enrollment.domain.model.Enrollment;

public interface CancelEnrollmentPort {
    Enrollment findById(long enrollmentId);
    void cancel(Enrollment enrollment);
}
