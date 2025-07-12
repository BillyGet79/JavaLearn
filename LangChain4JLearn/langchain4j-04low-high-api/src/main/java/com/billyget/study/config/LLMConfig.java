package com.billyget.study.config;

import com.billyget.study.service.ChatAssistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class LLMConfig {

    @Bean(value = "qwen")
    public ChatModel ChatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("Qwen-api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean(value = "deepseek")
    public ChatModel ChatModelDeepSeek() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("deepseek-api"))
                .modelName("deepseek-chat")
                .baseUrl("https://api.deepseek.com/v1")
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant(@Qualifier("qwen") ChatModel chatModelQwen) {
        return AiServices.create(ChatAssistant.class, chatModelQwen);
    }
}
