package cn.tycoding.langchat.common.dto;

import dev.langchain4j.model.input.Prompt;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2024/1/6
 */
@Data
@Accessors(chain = true)
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
