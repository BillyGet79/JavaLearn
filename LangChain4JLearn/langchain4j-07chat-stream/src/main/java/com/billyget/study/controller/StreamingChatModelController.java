package com.billyget.study.controller;

import com.billyget.study.service.ChatAssistant;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * StreamingChatModelController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/13
 * @description TODO
 */
@RestController
@Slf4j
@Tag(name = "StreamingChatModelController", description = "StreamingChatModelController")
public class StreamingChatModelController {

    private StreamingChatModel streamingChatModel;

    private ChatAssistant chatAssistant;

    public StreamingChatModelController(@Qualifier("qwen-stream") StreamingChatModel streamingChatModel,
                                        ChatAssistant chatAssistant) {
        this.streamingChatModel = streamingChatModel;
        this.chatAssistant = chatAssistant;
    }

    @GetMapping(value = "/streaming/chat")
    public Flux<String> chat(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        return Flux.create(emitter -> {
            streamingChatModel.chat(question, new StreamingChatResponseHandler() {
                @Override
                public void onPartialResponse(String s) {
                    emitter.next(s);
                }


                @Override
                public void onCompleteResponse(ChatResponse chatResponse) {
                    emitter.complete();
                }

                @Override
                public void onError(Throwable throwable) {
                    emitter.error(throwable);
                }
            });
        });
    }

    @GetMapping(value = "/streaming/chat2")
    public void chat2(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        log.info("---come in chat2");
        streamingChatModel.chat(question, new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String s) {
                System.out.print(s);
            }

            @Override
            public void onCompleteResponse(ChatResponse chatResponse) {
                System.out.println();
                log.info("complete response: {}", chatResponse);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @GetMapping(value = "/streaming/chat3")
    public Flux<String> streamingChat3(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        return chatAssistant.chatFlux(question);
    }
}
