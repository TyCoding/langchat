package cn.tycoding.langchat.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/9
 */
@Data
@ConfigurationProperties(prefix = "langchat.embedding.pinecone")
public class PineconeProps {

    private String apiKey;
    private String environment;
    private String nameSpace;
    private String projectId;
    private String index;
}
