package cn.tycoding.langchat.core.properties.embed;

import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Data
@ConfigurationProperties(prefix = "langchat.embedding.ollama")
public class OllamaEmbedProps {

    private String baseUrl;
    private String modelName;
    private Duration timeout;
    private Integer maxRetries;
}
