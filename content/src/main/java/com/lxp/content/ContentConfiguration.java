package com.lxp.content;

import com.lxp.common.infrastructure.persistence.JpaAuditingConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.ApplicationModule;

@Configuration
@ApplicationModule
// TODO(이 부분 추후 라이브러리에서 제거 후 application 에서 config 설정 )
@Import({JpaAuditingConfig.class})
public class ContentConfiguration {
}
