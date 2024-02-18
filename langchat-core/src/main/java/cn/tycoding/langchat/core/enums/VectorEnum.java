package cn.tycoding.langchat.core.enums;

/**
 * @author tycoding
 * @since 2023/11/8
 */
public enum VectorEnum {

    OPENAI("openai"),
    GLM("glm"),
    ;

    private String name;

    VectorEnum(String name) {
        this.name = name;
    }
}
