package com.lxp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class ApplicationModuleTests {

    /**
     * 프로젝트의 모듈 구조가 Modulith 규칙을 준수하는지 검증합니다.
     */
    @Test
    void verifyModularStructure() {
        ApplicationModules modules = ApplicationModules.of("com.lxp");
        modules.verify();
    }

    /**
     * (선택 사항) 모듈 간의 의존성 구조를 시각화하는 문서를 생성합니다.
     */
    @Test
    void createModuleDocumentation() {
        // 문서를 생성할 시작점을 지정합니다.
        ApplicationModules modules = ApplicationModules.of(MainApplication.class);

        // AsciiDoc 형식의 문서를 'build/generated-docs/modularity' 경로에 생성합니다.
        // 이 문서는 모듈 경계, 매핑, 모듈 간 의존성 다이어그램을 포함합니다.
        new Documenter(modules)
                .writeDocumentation();
    }
}