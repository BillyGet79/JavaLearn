package com.billyget.study.controller;

import com.billyget.study.service.ChatAssistant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * DeclarativeAIServiceController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@RestController
@Slf4j
public class DeclarativeAIServiceController {
    private ChatAssistant chatAssistant;

    public DeclarativeAIServiceController(ChatAssistant chatAssistant) {
        this.chatAssistant = chatAssistant;
    }

    @GetMapping(value = "/lc4j/boot/declarative")
    public String chat(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        return chatAssistant.chat(question);
    }
}
