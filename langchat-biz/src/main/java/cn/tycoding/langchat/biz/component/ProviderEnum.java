package cn.tycoding.langchat.biz.component;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tycoding
 * @since 2024/6/16
 */
@Getter
public enum ProviderEnum {

    OPENAI("openai"),
    AZURE_OPENAI("azure-openai"),
    GOOGLE("google"),
    OLLAMA("ollama"),
    BAIDU("baidu"),
    ALIBABA("alibaba"),
    ZHIPU("zhipu"),
    TEXT_IMAGE("text-image"),
    EMBEDDING("embedding"),
    WEB_SEARCH("web-search"),

    TEXT_IMAGE_DALLE2("dall-e-2"),
    TEXT_IMAGE_DALLE3("dall-e-3"),
    ;

    @Setter
    private String model;

    @Setter
    private String streamClass;

    ProviderEnum(String model) {
        this.model = model;
    }
}
