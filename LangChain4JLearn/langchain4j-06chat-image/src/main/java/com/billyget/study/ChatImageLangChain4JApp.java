package com.billyget.study;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ChatImageLangChain4JApp
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/13
 * @description TODO
 */
@SpringBootApplication
@Slf4j
public class ChatImageLangChain4JApp {
    public static void main(String[] args) {
        log.info("swagger-ui: {}" , "http://localhost:9006/swagger-ui/index.html");
        SpringApplication.run(ChatImageLangChain4JApp.class, args);
    }
}
