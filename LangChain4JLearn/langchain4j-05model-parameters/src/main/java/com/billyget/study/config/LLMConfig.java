package com.billyget.study.config;

import com.billyget.study.listeners.TestChatModelListener;
import dev.langchain4j.model.chat.ChatModel;

import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

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


    @Bean(value = "qwen")
    public ChatModel ChatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("Qwen-api"))
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                // 日志配置
                .logRequests(true)
                .logResponses(true)
                // 监控
                .listeners(List.of(new TestChatModelListener()))
                // 重试次数
                .maxRetries(3)
                // 请求超时
                .timeout(Duration.ofMinutes(1))
                .build();
    }
}
