package cn.tycoding.langchat.core.props.chat;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/15
 */
@Data
@ConfigurationProperties(prefix = "langchat.qianfan")
public class QianfanProps {

    private String baseUrl;
    private String apiKey;
    private String secretKey;
    private Double temperature;
    private Double topP;
    private String modelName;
    private String endpoint;
    private String responseFormat;
    private Double penaltyScore;
    private Boolean logRequests;
    private Boolean logResponses;
}
