package com.lxp.enrollment.interfaces;

import com.lxp.enrollment.application.port.provided.EnrollCourseUseCase;
import com.lxp.enrollment.application.port.provided.command.EnrollCourseCommand;
import com.lxp.enrollment.application.port.provided.dto.EnrollCourseResult;
import com.lxp.enrollment.interfaces.dto.request.EnrollCourseRequest;
import com.lxp.enrollment.interfaces.dto.response.EnrollCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentCommandController {
//    private final EnrollCourseUseCase enrollCourseUseCase;

//    @PostMapping
//    public ResponseEntity<EnrollCourseResponse> enrollCourse(@RequestBody EnrollCourseRequest request) {
//        EnrollCourseResult result = enrollCourseUseCase.enroll(new EnrollCourseCommand(
//                request.userId(),
//                request.courseId()
//        ));
//        EnrollCourseResponse response = new EnrollCourseResponse(
//                result.enrollmentId(),
//                request.
//        )
//    }
}
