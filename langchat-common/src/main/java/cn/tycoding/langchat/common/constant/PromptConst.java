package cn.tycoding.langchat.common.constant;

import lombok.Getter;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Getter
@SuppressWarnings("all")
public enum PromptConst {

    IMAGE("image"),
    LINE_CHART("line-chart"),
    MIND_MAP("mindmap"),
    WRITE("write"),
    TRANSLATE("translate"),
    DOCUMENT("document"),
    CHAT("chat"),
    ;

    private final String type;

    PromptConst(String type) {
        this.type = type;
    }
}
