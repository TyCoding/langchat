package cn.tycoding.langchat.core.props.chat;

import cn.tycoding.langchat.core.props.image.OpenaiImageProps;
import dev.langchain4j.model.Tokenizer;
import java.net.Proxy;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/15
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
    private Duration timeout;
    private Proxy proxy;
    private Boolean logRequests;
    private Boolean logResponses;
    private Tokenizer tokenizer;
}
