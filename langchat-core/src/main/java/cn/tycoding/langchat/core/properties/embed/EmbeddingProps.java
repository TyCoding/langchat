package cn.tycoding.langchat.core.properties.embed;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@ConfigurationProperties(prefix = "langchat.embedding")
public class EmbeddingProps {

    private OpenaiEmbedProps openai = new OpenaiEmbedProps();

    private AzureOpenaiEmbedProps azureopenai = new AzureOpenaiEmbedProps();

    private OllamaEmbedProps ollama = new OllamaEmbedProps();

}
