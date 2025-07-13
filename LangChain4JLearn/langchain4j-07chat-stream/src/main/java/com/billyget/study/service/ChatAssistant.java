package com.billyget.study.service;

import reactor.core.publisher.Flux;

/**
 * Assistant
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/13
 * @description TODO
 */
public interface ChatAssistant {

    String chat(String prompt);

    Flux<String> chatFlux(String prompt);
}
