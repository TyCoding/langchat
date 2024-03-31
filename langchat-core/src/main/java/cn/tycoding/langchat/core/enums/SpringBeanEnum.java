package cn.tycoding.langchat.core.enums;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

/**
 * @author tycoding
 * @since 2024/3/2
 */
@Getter
public enum SpringBeanEnum {

    /**
     * @see org.springframework.ai.autoconfigure.ollama.OllamaChatProperties
     */
    OLLAMA("ollama", """
            spring.ai.ollama.chat-org.springframework.ai.autoconfigure.ollama.OllamaChatProperties
            """),

    /**
     * @see org.springframework.ai.autoconfigure.openai.OpenAiChatProperties
     */
    OPENAI("openai", """
            spring.ai.openai.chat-org.springframework.ai.autoconfigure.openai.OpenAiChatProperties
            """),

    /**
     * @see org.springframework.ai.autoconfigure.azure.openai.AzureOpenAiChatProperties
     */
    AZURE_OPENAI("azure", """
            spring.ai.azure.openai.chat-org.springframework.ai.autoconfigure.azure.openai.AzureOpenAiChatProperties
            """),

    /**
     * @see org.springframework.ai.autoconfigure.vertexai.VertexAiChatProperties
     */
    VERTEXAI("vertexai", """
            spring.ai.vertexai.chat-org.springframework.ai.autoconfigure.vertexai.VertexAiChatProperties
            """),

    ;

    private final String name;
    private final String path;

    SpringBeanEnum(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public static Map<String, String> getNames() {
        Map<String, String> enumMap = new HashMap<>();

        for (SpringBeanEnum value : SpringBeanEnum.values()) {
            enumMap.put(value.name(), value.getName());
        }
        return enumMap;
    }
}
