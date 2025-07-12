package com.billyget.study.controller;

import dev.langchain4j.model.chat.ChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MultiModelController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@RestController
@RequestMapping("/multimodel")
@Slf4j
public class MultiModelController {

    private ChatModel chatModelQwen;

    private ChatModel chatModelDeepSeek;

    public MultiModelController(@Qualifier("qwen") ChatModel chatModelQwen,
                                @Qualifier("deepseek") ChatModel chatModelDeepSeek) {
        this.chatModelQwen = chatModelQwen;
        this.chatModelDeepSeek = chatModelDeepSeek;
    }


    @GetMapping(value = "/deepseek")
    public String multi(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        String result = chatModelDeepSeek.chat(question);
        log.info("result: {}", result);
        return result;
    }

    @GetMapping(value = "/qwen")
    public String qwen(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        String result = chatModelQwen.chat(question);
        log.info("result: {}", result);
        return result;
    }
}
