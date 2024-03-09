package cn.tycoding.langchat.core.dto;

import dev.langchain4j.model.input.Prompt;
import lombok.Data;

/**
 * @author tycoding
 * @since 2024/1/6
 */
@Data
public class ImageR {

    private Prompt prompt;

    /**
     * 内容
     */
    private String message;

    /**
     * 模型
     */
    private String model;

    /**
     * 质量
     */
    private String quality;

    /**
     * 尺寸
     */
    private String size;

    /**
     * 风格
     */
    private String style;
}
