package cn.tycoding.langchat.core.utils;

import lombok.Getter;

/**
 * @author tycoding
 * @since 2024/1/6
 */
@Getter
public enum FileEnum {

    /**
     * 用户输入的文档
     */
    INPUT("Input"),

    /**
     * 用户输出的文档
     */
    OUTPUT("Output"),
    ;

    /**
     * 文件来源渠道
     */
    private final String channel;

    FileEnum(String channel) {
        this.channel = channel;
    }
}
