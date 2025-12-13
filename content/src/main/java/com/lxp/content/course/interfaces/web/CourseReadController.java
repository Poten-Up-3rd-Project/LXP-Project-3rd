package com.lxp.content.course.interfaces.web;


import com.lxp.common.domain.pagination.Page;
import com.lxp.common.domain.pagination.PageRequest;
import com.lxp.common.domain.pagination.Sort;
import com.lxp.content.course.interfaces.web.dto.response.CourseResponse;
import com.lxp.content.course.interfaces.web.mapper.CourseApplicationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-v1/courses")
@RequiredArgsConstructor
public class CourseReadController {

    private final CourseApplicationFacade facade;

    @GetMapping
    public ResponseEntity<Page<CourseResponse>> getCourses(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value ="sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir
            ) {
        return ResponseEntity.ok(facade.getCourses(PageRequest.of(page, size, Sort.ofNullable(sort, dir))));
    }
}
