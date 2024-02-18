package cn.tycoding.langchat.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/15
 */
@Data
@ConfigurationProperties(prefix = "langchat.ollama")
public class OllamaProps {

    private String baseUrl;
    private Integer timeout;
    private String modelName;
    private Double temperature;
    private Integer maxRetries;
}
