package com.lxp.content.course.application.port.required;


import com.lxp.common.application.port.out.read.ReadModelRepository;
import com.lxp.content.course.application.port.required.dto.CourseReadModel;

import java.util.List;

// search에는 tag랑 강사이름과 제목 다 포함
public interface CourseReadModelPort extends ReadModelRepository<CourseReadModel, Long> {


    // 근데 문제는 tag는 readJpa에서 처리가 안됨 왜냐? tag는 json이니까
    // 일단 검색하면 redis에서 name로 id 검색 후 그 id로 jpa에서 course 검색
    // 그럼 readmodel에서 tag를 id로 가지고 있어야 되나? // 근데 그럼 readmodel이 read가 맞는가?
    //Page<CourseReadModel> search(String keyword);

    void save(CourseReadModel course);
}
