package cn.tycoding.langchat.common.dto;

import cn.tycoding.langchat.common.utils.StreamEmitter;
import dev.langchain4j.model.input.Prompt;
import lombok.Data;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Data
public class TextR {

    private StreamEmitter emitter;
    private Prompt prompt;

    private String model;

    /**
     * 输入内容
     */
    private String message;

    /**
     * 角色
     */
    private String role;

    /**
     * 输出内容类型
     */
    private String type;

    /**
     * 输出内容语言
     */
    private String language;

    /**
     * 输出内容语气
     */
    private String tone;

    /**
     * 输出内容长度
     */
    private String length;
}
