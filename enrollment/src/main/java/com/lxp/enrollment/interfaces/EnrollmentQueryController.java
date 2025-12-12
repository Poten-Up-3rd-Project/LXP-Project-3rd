package com.lxp.enrollment.interfaces;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentQueryController {
    @GetMapping
    public ResponseEntity<String> getEnrollments() {
        return ResponseEntity.ok("test");
    }
}
