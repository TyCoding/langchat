package cn.tycoding.langchat.core.properties.chat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@ConfigurationProperties(prefix = "langchat.ollama")
public class OllamaProps {

    private String baseUrl;
    private String modelName;
    private Double temperature;
    private Integer topK;
    private Double topP;
    private Double repeatPenalty;
    private Integer seed;
    private Integer numPredict;
    private List<String> stop;
    private String format;
    private Duration timeout = Duration.ofSeconds(600);
}
