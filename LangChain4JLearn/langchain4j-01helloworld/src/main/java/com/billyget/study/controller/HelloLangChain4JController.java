package com.billyget.study.controller;

import dev.langchain4j.model.chat.ChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloLangChain4JController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@RestController
@Slf4j
public class HelloLangChain4JController {

    private ChatModel chatModel;

    @Autowired
    public HelloLangChain4JController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping(value = "/langchain4j/hello")
    public String hello(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        String result = chatModel.chat(question);
        // 调用大模型回复
        log.info("result: {}", result);
        return result;
    }

}
