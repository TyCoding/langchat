package cn.tycoding.langchat.core.properties.search;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/6/7
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "langchat.web-search.tavily")
public class TavilyProps {

    private String baseUrl;
    private String apiKey;
    private Duration timeout = Duration.ofSeconds(600);
    private String searchDepth;
    private Boolean includeAnswer;
    private Boolean includeRawContent;
    private List<String> includeDomains;
    private List<String> excludeDomains;
}
