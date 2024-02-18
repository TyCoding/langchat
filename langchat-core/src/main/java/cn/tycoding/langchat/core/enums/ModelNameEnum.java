package cn.tycoding.langchat.core.enums;

/**
 * @author tycoding
 * @since 2023/11/8
 */
public enum ModelNameEnum {

    GPT4("gpt-4"),
    GPT3("gpt-3.5-turbo"),
    ;

    private String name;

    ModelNameEnum(String name) {
        this.name = name;
    }
}
