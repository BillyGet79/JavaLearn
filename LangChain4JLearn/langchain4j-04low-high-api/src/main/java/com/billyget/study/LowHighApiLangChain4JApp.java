package com.billyget.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * LowHighApiLangChain4JApp
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@SpringBootApplication
@Slf4j
public class LowHighApiLangChain4JApp {
    public static void main(String[] args) {
        log.info("swagger-ui: {}" , "http://localhost:9004/swagger-ui/index.html");
        SpringApplication.run(LowHighApiLangChain4JApp.class, args);
    }
}
