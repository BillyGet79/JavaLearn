package com.billyget.study.controller;

import com.billyget.study.service.ChatAssistant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HighApiController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@RestController
@Slf4j
@Tag(name = "HighApiController", description = "HighApiController")
public class HighApiController {
    private ChatAssistant chatAssistant;

    public HighApiController(ChatAssistant chatAssistant) {
        this.chatAssistant = chatAssistant;
    }

    @GetMapping(value = "/highapi/api01")
    @Operation(summary = "高阶API问答", description = "简单问答")
    public String api01(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        return chatAssistant.chat(question);
    }
}
