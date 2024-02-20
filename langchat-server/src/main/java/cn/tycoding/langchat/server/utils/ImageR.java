package cn.tycoding.langchat.server.utils;

import lombok.Data;

/**
 * @author tycoding
 * @since 2024/1/6
 */
@Data
public class ImageR {

    /**
     * 内容
     */
    private String content;

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
