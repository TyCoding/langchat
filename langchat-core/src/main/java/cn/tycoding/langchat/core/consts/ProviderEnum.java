package cn.tycoding.langchat.core.consts;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tycoding
 * @since 2024/6/16
 */
public enum ProviderEnum {

    OPENAI("openai"),
    GOOGLE("google"),
    OLLAMA("ollama"),
    BAIDU("baidu"),
    ALIBABA("alibaba"),
    ;

    @Setter
    @Getter
    private String model;

    @Setter
    @Getter
    private String streamClass;

    ProviderEnum(String model) {
        this.model = model;
    }
}
