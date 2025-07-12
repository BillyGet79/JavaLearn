package com.billyget.study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ChatResponse
 *
 * @author 29096
 * @version 1.0
 * @date 2025/7/12
 * @description TODO
 */
@Data
@AllArgsConstructor
public class ChatResponseDto {
    private String content;
    private Integer usage;
}
