package com.lxp.user.application.port.required;

import com.lxp.user.application.port.required.dto.TagResult;

import java.util.List;

public interface UserTagQueryPort {

    List<TagResult> findTagByIds(List<Long> tagId);

}
