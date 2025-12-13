package com.lxp.content.course.interfaces.web;

import com.lxp.common.annotation.CurrentUserId;
import com.lxp.common.infrastructure.exception.ApiResponse;
import com.lxp.content.course.interfaces.web.dto.response.CourseDetailResponse;
import com.lxp.content.course.interfaces.web.dto.reuqest.create.CourseCreateRequest;
import com.lxp.content.course.interfaces.web.mapper.CourseApplicationFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseApplicationFacade facade;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseDetailResponse>> create(@CurrentUserId String userId,
           @RequestBody CourseCreateRequest input) {
        CourseDetailResponse response = facade.createCourse(userId,input);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
