package cn.tycoding.langchat.server.utils;

import cn.tycoding.langchat.core.utils.StreamEmitter;
import lombok.Data;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Data
public class TextR {

    private StreamEmitter emitter;

    /**
     * 输入内容
     */
    private String content;

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
