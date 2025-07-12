package com.billyget.study.controller;

import dev.langchain4j.model.chat.ChatModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ModelParameterController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@RestController
@Slf4j
@Tag(name = "ModelParameterController", description = "ModelParameterController")
public class ModelParameterController {

    private ChatModel chatModelQwen;

    public ModelParameterController(@Qualifier("qwen") ChatModel chatModelQwen) {
        this.chatModelQwen = chatModelQwen;
    }

    @GetMapping(value = "/modelparameter/config")
    public String config(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        return chatModelQwen.chat(question);
    }
}
