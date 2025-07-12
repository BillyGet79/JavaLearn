package com.billyget.study.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "LangChain4J API", version = "1.0", description = "LangChain4J API 文档"))
public class SwaggerConfig {
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("LangChain4J API")
                .packagesToScan("com.billyget.study.controller")
                .build();
    }
}
