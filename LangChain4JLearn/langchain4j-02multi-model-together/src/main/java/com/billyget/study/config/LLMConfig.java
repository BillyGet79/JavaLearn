package com.billyget.study.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LLMConfig
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@Configuration
@Slf4j
public class LLMConfig {

    @Bean(name = "deepseek")
    public ChatModel ChatModelDeepSeek() {
        log.info("deepseek-api: {}", System.getenv("deepseek-api"));
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("deepseek-api"))
                .modelName("deepseek-chat")
                .baseUrl("https://api.deepseek.com/v1")
                .build();
    }

    @Bean(name = "qwen")
    public ChatModel ChatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("Qwen-api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }
}
