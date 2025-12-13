package com.lxp.content.course.application.port.required;


import com.lxp.common.application.port.out.read.ReadModelRepository;
import com.lxp.common.domain.pagination.Page;
import com.lxp.common.domain.pagination.PageRequest;
import com.lxp.content.course.application.port.required.dto.CourseReadModel;

// search에는 tag랑 강사이름과 제목 다 포함
public interface CourseReadModelPort extends ReadModelRepository<CourseReadModel, Long> {

    Page<CourseReadModel> search(String keyword, PageRequest pageable);
    void save(CourseReadModel course);
}
