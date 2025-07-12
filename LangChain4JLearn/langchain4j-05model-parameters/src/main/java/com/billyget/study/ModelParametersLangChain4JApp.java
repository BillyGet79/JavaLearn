package com.billyget.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ModelParametersLangChain4JApp
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@SpringBootApplication
@Slf4j
public class ModelParametersLangChain4JApp {
    public static void main(String[] args) {
        log.info("swagger-ui: {}" , "http://localhost:9005/swagger-ui/index.html");
        SpringApplication.run(ModelParametersLangChain4JApp.class, args);
    }
}
