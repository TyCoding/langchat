package cn.tycoding.langchat.common.constant;

import lombok.Getter;

/**
 * @author tycoding
 * @since 2024/2/20
 */
@Getter
public enum RoleEnum {
    USER("user"),
    ASSISTANT("assistant"),
    SYSTEM("system"),
    ;

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }
}
