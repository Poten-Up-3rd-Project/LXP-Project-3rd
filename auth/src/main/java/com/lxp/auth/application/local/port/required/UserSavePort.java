package com.lxp.auth.application.local.port.required;

import com.lxp.auth.application.local.port.required.dto.UserRegistrationInfo;

public interface UserSavePort {

    /**
     * 외부 사용자 시스템에 사용자 등록 정보를 저장합니다.
     *
     * @param info Auth 모듈의 도메인에서 생성된 사용자 등록 VO
     */
    void save(UserRegistrationInfo info);
}
