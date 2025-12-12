package com.lxp.recommend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * Recommend BC 통합 테스트
 */
@SpringBootTest
@ContextConfiguration(classes = RecommendApplication.class)  // ← 메인 클래스 명시
class RecommendApplicationTests {

    @Test
    void contextLoads() {
        // Spring Context 로딩 테스트
    }
}
