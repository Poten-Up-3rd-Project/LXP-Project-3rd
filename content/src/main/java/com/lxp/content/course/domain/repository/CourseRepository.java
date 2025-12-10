package com.lxp.content.course.domain.repository;

import com.lxp.content.course.domain.model.Course;

import java.util.Optional;

public interface CourseRepository {
    Course save(Course course);
    Optional<Course> findByUUID(String courseUUID);
}
