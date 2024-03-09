package cn.tycoding.langchat.core.props.chat;

import com.azure.core.http.ProxyOptions;
import dev.langchain4j.model.Tokenizer;
import java.time.Duration;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/15
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
    private Duration timeout;
    private Long seed;
    private Integer maxRetries;
    private ProxyOptions proxyOptions;
    private boolean logRequestsAndResponses;
}
