package com.billyget.study.config;

import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LLMConfig
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/13
 * @description TODO
 */
@Configuration
public class LLMConfig {
    @Bean(value = "qwen-chat")
    public ChatModel chatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("Qwen-api"))
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .modelName("qwen-plus")
                .build();
    }

    @Bean(value = "qwen-image")
    public ChatModel chatModelQwenImage() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("Qwen-api"))
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .modelName("qwen-vl-max")
                .build();
    }

    @Bean(value = "wanx-image")
    public WanxImageModel wanxImageModel() {
        return WanxImageModel.builder()
                .apiKey(System.getenv("Qwen-api"))
                .modelName("wanx2.1-t2i-turbo")
                .build();
    }
}
