package cn.tycoding.langchat.core.properties.vectorstore;

import io.milvus.param.IndexType;
import io.milvus.param.MetricType;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "langchat.vectorstore.milvus")
public class MilvusProps {

    private String host;
    private Integer port;
    private String collectionName;
    private Integer dimension;
    private IndexType indexType;
    private MetricType metricType;
    private String uri;
    private String token;
    private String username;
    private String password;
    private Boolean retrieveEmbeddingsOnSearch;
    private String databaseName;
}
