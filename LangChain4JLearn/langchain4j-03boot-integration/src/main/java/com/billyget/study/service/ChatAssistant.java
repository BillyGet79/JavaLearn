package com.billyget.study.service;

import dev.langchain4j.service.spring.AiService;

/**
 * ChatAssistant
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@AiService
public interface ChatAssistant {
    String chat(String prompt);
}
