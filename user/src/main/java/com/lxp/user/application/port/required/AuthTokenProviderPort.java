package com.lxp.user.application.port.required;

import com.lxp.user.application.port.required.dto.AuthTokenResult;
import com.lxp.user.application.port.required.dto.TokenRegenerationDto;

public interface AuthTokenProviderPort {

    /**
     * Auth 모듈에게 사용자 정보를 기반으로 토큰 재생성을 요청합니다.
     *
     * @param command 토큰 재생성에 필요한 사용자 ID, 이메일, 역할 등을 포함하는 커맨드 객체
     * @return 새로운 인증 정보(토큰)를 담은 응답 객체 (통신 실패 가능성을 위해 Optional 사용)
     */
    AuthTokenResult regenerateToken(TokenRegenerationDto command);
}
