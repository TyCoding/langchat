package cn.tycoding.langchat.core.properties.search;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author tycoding
 * @since 2024/6/6
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "langchat.web-search.google")
public class GoogleProps {

    private String apiKey;
    private String csi;
    private Boolean siteRestrict;
    private Boolean includeImages;
    private Duration timeout = Duration.ofSeconds(600);
    private Integer maxRetries;
    private Boolean logRequests;
    private Boolean logResponses;
}
