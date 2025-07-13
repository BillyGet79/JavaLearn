package com.billyget.study.config;

import com.billyget.study.service.ChatAssistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
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

    /**
     * 普通对话接口
     *
     * @return dev.langchain4j.model.chat.ChatModel
     * @author 29096
     * @date 2025/7/13
     */
    @Bean(name = "qwen")
    public ChatModel qwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("Qwen-api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    /**
     * 流式对话接口
     *
     * @return dev.langchain4j.model.chat.StreamingChatModel
     * @author 29096
     * @date 2025/7/13
     */
    @Bean(name = "qwen-stream")
    public StreamingChatModel qwenStream() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("Qwen-api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public ChatAssistant chatAssistant(@Qualifier("qwen-stream") StreamingChatModel streamingChatModel) {
        return AiServices.create(ChatAssistant.class, streamingChatModel);
    }
}
