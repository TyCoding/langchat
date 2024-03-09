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
     * @see org.springframework.ai.autoconfigure.huggingface.HuggingfaceChatProperties
     */
    HUGGINGFACE("huggingface", """
            spring.ai.huggingface.chat-org.springframework.ai.autoconfigure.huggingface.HuggingfaceChatProperties
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

    /**
     * @see org.springframework.ai.autoconfigure.bedrock.anthropic.BedrockAnthropicChatProperties
     */
    BEDROCK_ANTHROPIC("bedrock.anthropic", """
            spring.ai.bedrock.anthropic.chat-org.springframework.ai.autoconfigure.bedrock.anthropic.BedrockAnthropicChatProperties
            """),

    /**
     * @see org.springframework.ai.autoconfigure.bedrock.cohere.BedrockCohereChatProperties
     */
    BEDROCK_COHERE("bedrock.cohere", """
            spring.ai.bedrock.cohere.chat-org.springframework.ai.autoconfigure.bedrock.cohere.BedrockCohereChatProperties
            """),

    /**
     * @see org.springframework.ai.autoconfigure.bedrock.llama2.BedrockLlama2ChatProperties
     */
    BEDROCK_LLAMA2("bedrock.llama2", """
            spring.ai.bedrock.llama2.chat-org.springframework.ai.autoconfigure.bedrock.llama2.BedrockLlama2ChatProperties
            """),

    /**
     * @see org.springframework.ai.autoconfigure.bedrock.titan.BedrockTitanChatProperties
     */
    BEDROCK_TITAN("bedrock.titan", """
            spring.ai.bedrock.titan.chat-org.springframework.ai.autoconfigure.bedrock.titan.BedrockTitanChatProperties
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
