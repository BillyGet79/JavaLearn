package com.billyget.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ChatStreamLangChain4JApp
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/13
 * @description TODO
 */
@SpringBootApplication
@Slf4j
public class ChatStreamLangChain4JApp {
    public static void main(String[] args) {
        log.info("swagger-ui: {}" , "http://localhost:9007/swagger-ui/index.html");
        SpringApplication.run(ChatStreamLangChain4JApp.class, args);
    }
}
