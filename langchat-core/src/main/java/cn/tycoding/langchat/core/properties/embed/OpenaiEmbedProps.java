package cn.tycoding.langchat.core.properties.embed;

import java.net.Proxy;
import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Data
@ConfigurationProperties(prefix = "langchat.embedding.openai")
public class OpenaiEmbedProps {

    private String baseUrl;
    private String apiKey;
    private String organizationId;
    private String modelName;
    private Integer dimensions;
    private String user;
    private Duration timeout;
    private Integer maxRetries;
    private Proxy proxy;
    private Boolean logRequests;
    private Boolean logResponses;
}
