package cn.tycoding.langchat.core.properties;

import cn.tycoding.langchat.core.enums.ChatModelEnum;
import cn.tycoding.langchat.core.enums.ModelNameEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/11/8
 */
@Data
@ConfigurationProperties(prefix = "langchat")
public class LangChatProps {

    /**
     * 模型密钥
     */
    private String apiKey;
    /**
     * 对话模型
     */
    private ChatModelEnum chatModel;
    /**
     * 模型名称
     */
    private ModelNameEnum modelName;
    /**
     * URL
     */
    private String url;
    /**
     * Proxy
     */
    private String proxy;

    private EmbedProps embedding;

    private OllamaProps ollama;

    private GeminiProps gemini;
}
