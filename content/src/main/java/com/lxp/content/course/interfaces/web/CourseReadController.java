package com.lxp.content.course.interfaces.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-v1/courses")
@RequiredArgsConstructor
public class CourseReadController {

    @GetMapping
    public ResponseEntity<String> getCourses() {
        return ResponseEntity.ok("Course list");
    }
}
