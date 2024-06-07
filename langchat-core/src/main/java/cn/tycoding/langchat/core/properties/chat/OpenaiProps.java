package cn.tycoding.langchat.core.properties.chat;

import cn.tycoding.langchat.core.properties.image.OpenaiImageProps;
import dev.langchain4j.model.Tokenizer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.Proxy;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@ConfigurationProperties(prefix = "langchat.openai")
public class OpenaiProps {

    private OpenaiImageProps image = new OpenaiImageProps();

    private String baseUrl;
    private String apiKey;
    private String organizationId;
    private String modelName;
    private Double temperature;
    private Double topP;
    private List<String> stop;
    private Integer maxTokens;
    private Double presencePenalty;
    private Double frequencyPenalty;
    private Map<String, Integer> logitBias;
    private String responseFormat;
    private Integer seed;
    private String user;
    private Duration timeout = Duration.ofSeconds(3 * 60);
    private Proxy proxy;
    private Boolean logRequests;
    private Boolean logResponses;
    private Tokenizer tokenizer;
}
