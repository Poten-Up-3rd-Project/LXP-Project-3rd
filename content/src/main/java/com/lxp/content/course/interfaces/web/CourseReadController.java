package com.lxp.content.course.interfaces.web;


import com.lxp.common.domain.pagination.Page;
import com.lxp.common.domain.pagination.PageRequest;
import com.lxp.common.domain.pagination.Sort;
import com.lxp.common.infrastructure.exception.ApiResponse;
import com.lxp.content.course.interfaces.web.dto.response.CourseDetailResponse;
import com.lxp.content.course.interfaces.web.dto.response.CourseResponse;
import com.lxp.content.course.interfaces.web.mapper.CourseApplicationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-v1/courses")
@RequiredArgsConstructor
public class CourseReadController {

    private final CourseApplicationFacade facade;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<CourseResponse>>> getAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value ="sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir
            ) {

        return ResponseEntity.ok(ApiResponse.success(facade.getCourses(
                                PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(dir.toUpperCase()), sort))
                                )
                        )
                );
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<ApiResponse<CourseDetailResponse>> get(
            @PathVariable("courseId") String courseId
    ){
        return ResponseEntity.ok(ApiResponse.success(facade.getCourseDetail(courseId)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<CourseResponse>>> search(
            @RequestParam(value = "keyword") String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value ="sort", defaultValue = "createdAt") String sort,
            @RequestParam(value = "dir", defaultValue = "DESC") String dir
    ) {
        return ResponseEntity.ok(ApiResponse.success(facade.searchCourse(
                                PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(dir.toUpperCase()), sort)),
                        keyword
                        )
                )
        );
    }
}
