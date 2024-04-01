package cn.tycoding.langchat.common.utils;

import cn.tycoding.langchat.common.dto.PromptConst;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/3/1
 */
public class PromptUtil {

    public static Prompt build(String message) {
        return new Prompt(message);
    }

    public static Prompt build(String message, String promptText) {
        return new PromptTemplate(promptText).apply(Map.of(PromptConst.TMP, message));
    }
}
