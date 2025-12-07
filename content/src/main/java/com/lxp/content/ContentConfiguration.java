package com.lxp.content;

import com.lxp.common.infrastructure.persistence.JpaAuditingConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.ApplicationModule;

@Configuration
@ApplicationModule
@Import(JpaAuditingConfig.class)
public class ContentConfiguration {
}
