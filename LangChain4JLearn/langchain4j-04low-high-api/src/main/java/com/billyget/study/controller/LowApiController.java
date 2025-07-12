package com.billyget.study.controller;

import com.billyget.study.dto.ChatResponseDto;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.TokenUsage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * LowApiController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@RestController
@Slf4j
@Tag(name = "LowApiController", description = "LowApiController")
public class LowApiController {

    private ChatModel chatModelQwen;
    private ChatModel chatModelDeepSeek;

    public LowApiController(@Qualifier("qwen") ChatModel chatModelQwen,
                            @Qualifier("deepseek") ChatModel chatModelDeepSeek) {
        this.chatModelQwen = chatModelQwen;
        this.chatModelDeepSeek = chatModelDeepSeek;
    }

    @GetMapping(value = "/lowapi/api01")
    public String api01(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        return chatModelQwen.chat(question);
    }

    @GetMapping(value = "/lowapi/api02")
    @Operation(summary = "token用量计费", description = "token用量计费")
    public ChatResponseDto api02(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        ChatResponse chatResponse = chatModelDeepSeek.chat(UserMessage.from(question));
        String result = chatResponse.aiMessage().text();
        log.info("result: {}", result);
        TokenUsage tokenUsage = chatResponse.tokenUsage();
        log.info("tokenUsage: {}", tokenUsage);
        return new ChatResponseDto(result, tokenUsage.totalTokenCount());
    }
}
