package com.lxp.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.modulith.ApplicationModule;

/**
 * api 모듈은 Port, UseCase, Command, Result DTO를 정의하는 공유 계약(Contract) 모듈입니다.
 * 다른 도메인 모듈(content, tag 등)에서 이 모듈의 타입들을 참조할 수 있도록 OPEN 타입으로 설정합니다.
 */
@Configuration
@ApplicationModule(type = ApplicationModule.Type.OPEN)
public class ApiConfiguration {
}
