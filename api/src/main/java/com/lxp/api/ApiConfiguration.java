package com.lxp.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.modulith.ApplicationModule;

@Configuration
@ApplicationModule(allowedDependencies = {"common"})
public class ApiConfiguration {
}
