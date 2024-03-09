package cn.tycoding.langchat.core.props.chat;

import java.time.Duration;
import java.util.List;
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
    private String modelName;
    private Double temperature;
    private Integer topK;
    private Double topP;
    private Double repeatPenalty;
    private Integer seed;
    private Integer numPredict;
    private List<String> stop;
    private String format;
    private Duration timeout;
}
