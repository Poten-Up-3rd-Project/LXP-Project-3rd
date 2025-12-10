package com.lxp.enrollment.interfaces;

import com.lxp.enrollment.interfaces.dto.request.EnrollmentListRequest;
import com.lxp.enrollment.interfaces.dto.response.EnrollmentPageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EnrollmentQueryController {

    public ResponseEntity<EnrollmentPageResponse> getEnrollments(EnrollmentListRequest request) {
        return null;
    }
}
