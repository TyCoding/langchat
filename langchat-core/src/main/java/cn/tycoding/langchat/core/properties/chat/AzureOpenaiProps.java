package cn.tycoding.langchat.core.properties.chat;

import com.azure.core.http.ProxyOptions;
import dev.langchain4j.model.Tokenizer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@ConfigurationProperties(prefix = "langchat.azure-openai")
public class AzureOpenaiProps {

    private String endpoint;
    private String serviceVersion;
    private String apiKey;
    private String deploymentName;
    private Tokenizer tokenizer;
    private Integer maxTokens;
    private Double temperature;
    private Double topP;
    private String user;
    private Integer n;
    private List<String> stop;
    private Double presencePenalty;
    private Double frequencyPenalty;
    private Duration timeout = Duration.ofSeconds(3 * 60);
    private Long seed;
    private Integer maxRetries;
    private ProxyOptions proxyOptions;
    private boolean logRequestsAndResponses;
}
