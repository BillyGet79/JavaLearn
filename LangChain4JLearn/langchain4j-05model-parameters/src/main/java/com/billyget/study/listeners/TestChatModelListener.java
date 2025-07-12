package com.billyget.study.listeners;

import cn.hutool.core.util.IdUtil;
import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;

/**
 * TestChatModelListener
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description ChatModelListener LLM调用监听器定义
 */
@Slf4j
public class TestChatModelListener implements ChatModelListener {
    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        String uuidValue = IdUtil.simpleUUID();
        requestContext.attributes().put("TraceID", uuidValue);
        log.info("request: {}", requestContext + "\t" + uuidValue);
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        Object object = responseContext.attributes().get("TraceID");
        log.info("response: {}", object);
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        log.error("error: {}", errorContext);
    }
}
