package com.lxp.enrollment.interfaces;

import com.lxp.enrollment.application.port.provided.dto.command.EnrollCourseCommand;
import com.lxp.enrollment.application.port.provided.dto.result.EnrollCourseResult;
import com.lxp.enrollment.application.usecase.EnrollCourseUseCase;
import com.lxp.enrollment.interfaces.dto.request.EnrollCourseRequest;
import com.lxp.enrollment.interfaces.dto.response.EnrollCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentCommandController {
    private final EnrollCourseUseCase enrollCourseUseCase;

    @PostMapping
    public ResponseEntity<EnrollCourseResponse> enrollCourse(@RequestBody EnrollCourseRequest request) {
        EnrollCourseResult result = enrollCourseUseCase.enroll(new EnrollCourseCommand(
                request.userId(),
                request.courseId()
        ));
        EnrollCourseResponse response = new EnrollCourseResponse(
                result.enrollmentId(),
                result.state(),
                result.enrolledAt()
        );
        return ResponseEntity.ok(response);
    }
}
