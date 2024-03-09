package cn.tycoding.langchat.core.props.chat;

import java.time.Duration;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/15
 */
@Data
@ConfigurationProperties(prefix = "langchat.huggingface")
public class HuggingfaceProps {

    private String accessToken;
    private String modelId;
    private Duration timeout;
    private Double temperature;
    private Integer maxNewTokens;
    private Boolean returnFullText;
    private Boolean waitForModel;
}
