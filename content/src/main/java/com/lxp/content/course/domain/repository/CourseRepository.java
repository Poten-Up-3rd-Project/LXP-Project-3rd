package com.lxp.content.course.domain.repository;

import com.lxp.content.course.domain.model.Course;

public interface CourseRepository {
    Course save(Course course);
}
