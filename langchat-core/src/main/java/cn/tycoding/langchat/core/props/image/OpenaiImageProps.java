package cn.tycoding.langchat.core.props.image;

import java.net.Proxy;
import java.nio.file.Path;
import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Data
@ConfigurationProperties(prefix = "langchat.openai.image")
public class OpenaiImageProps {

    private String baseUrl;
    private String apiKey;
    private String organizationId;
    private String modelName;
    private String size;
    private String quality;
    private String style;
    private String user;
    private String responseFormat;
    private Duration timeout;
    private Integer maxRetries;
    private Proxy proxy;
    private Boolean logRequests;
    private Boolean logResponses;
    private Boolean withPersisting;
    private Path persistTo;
}
