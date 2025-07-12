package com.billyget.study.controller;

import dev.langchain4j.model.chat.ChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * PopularIntegrationController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@RestController
@Slf4j
public class PopularIntegrationController {
    private ChatModel chatModel;

    public PopularIntegrationController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping(value = "/lc4j/boot/chat")
    public String chat(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        return chatModel.chat(question);
    }
}
