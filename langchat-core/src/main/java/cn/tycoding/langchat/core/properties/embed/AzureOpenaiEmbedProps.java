package cn.tycoding.langchat.core.properties.embed;

import com.azure.core.http.ProxyOptions;
import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Data
@ConfigurationProperties(prefix = "langchat.embedding.azureopenai")
public class AzureOpenaiEmbedProps {

    private String endpoint;
    private String serviceVersion;
    private String apiKey;
    private String deploymentName;
    private Duration timeout;
    private Integer maxRetries;
    private ProxyOptions proxyOptions;
    private boolean logRequestsAndResponses;
}
