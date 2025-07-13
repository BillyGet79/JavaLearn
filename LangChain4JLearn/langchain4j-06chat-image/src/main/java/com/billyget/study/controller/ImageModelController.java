package com.billyget.study.controller;

import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

/**
 * ImageModelController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/13
 * @description TODO
 */
@RestController
@Slf4j
@Tag(name = "ImageModelController", description = "ImageModelController")
public class ImageModelController {

    private ChatModel chatModelQwenImage;

    private Resource resource;


    public ImageModelController(@Qualifier("qwen-image") ChatModel chatModelQwenImage,
                                @Value("classpath:static/images/mi.jpg") Resource resource) {
        this.chatModelQwenImage = chatModelQwenImage;
        this.resource = resource;
    }

    @GetMapping(value = "/image/qwen")
    public String readImageContent() throws IOException {
        String result = null;

        // 第一步，图片转码，通过Base64编码将图片转化为字符串
        byte[] byteArray = resource.getContentAsByteArray();
        String base64Data = Base64.getEncoder().encodeToString(byteArray);
        // 第二步，提示词指定：结合ImageContent和TextContent一起发送到模型进行处理
        UserMessage userMessage = UserMessage.from(
                TextContent.from("从下面图片中获取来源网站名称, 股价走势和5月30号股价"),
                ImageContent.from(base64Data, "image/jpg")
        );
        // 第三步，API调用：使用OpenAiChatModel来构建请求，并通过chat方法调用模型
        // 请求内容包括文本提示和图片，模型会根据输入返回分析结果
        ChatResponse chatResponse = chatModelQwenImage.chat(userMessage);
        // 第四步：解析与输出：从ChatResponse中获取AI大模型的回复，答应出处理后的结果
        result = chatResponse.aiMessage().text();
        // 后台打印
        log.info("result: {}", result);

        return result;
    }

}
