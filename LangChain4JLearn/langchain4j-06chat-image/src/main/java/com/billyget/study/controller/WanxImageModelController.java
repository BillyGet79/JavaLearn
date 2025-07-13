package com.billyget.study.controller;

import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * WanxImageModelController
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/13
 * @description TODO
 */
@RestController
@Slf4j
@Tag(name = "WanxImageModelController", description = "WanxImageModelController")
public class WanxImageModelController {

    private WanxImageModel wanxImageModel;

    public WanxImageModelController(@Qualifier("wanx-image") WanxImageModel wanxImageModel) {
        this.wanxImageModel = wanxImageModel;
    }

    @GetMapping(value = "/image/wanx")
    public String createImageContent(@RequestParam(value = "prompt", defaultValue = "小狗") String prompt) {
        log.info("wanxImageModel: {}", wanxImageModel);
        Response<Image> imageResponse = wanxImageModel.generate(prompt);
        log.info("url: {}", imageResponse.content().url());
        return imageResponse.content().url().toString();
    }
}
