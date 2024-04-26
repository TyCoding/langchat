package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.vectorstore.MilvusProps;
import dev.langchain4j.internal.Utils;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
import io.milvus.client.MilvusServiceClient;
import io.milvus.param.ConnectParam;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Configuration
@AllArgsConstructor
public class VectorStoreComponent {

    private final LangChatProps props;

    @Bean
    @ConditionalOnProperty(value = "langchat.vectorstore.milvus.host", matchIfMissing = false)
    public MilvusEmbeddingStore milvusEmbeddingStore() {
        MilvusProps prop = props.getVectorstore().getMilvus();
        return MilvusEmbeddingStore.builder()
                .host(prop.getHost())
                .port(prop.getPort())
                .collectionName(prop.getCollectionName())
                .dimension(prop.getDimension())
                .indexType(prop.getIndexType())
                .metricType(prop.getMetricType())
                .uri(prop.getUri())
                .token(prop.getToken())
                .username(prop.getUsername())
                .password(prop.getPassword())
                .retrieveEmbeddingsOnSearch(prop.getRetrieveEmbeddingsOnSearch())
                .databaseName(prop.getDatabaseName())
                .build();
    }

    @Bean
    @ConditionalOnProperty(value = "langchat.vectorstore.milvus.host", matchIfMissing = false)
    public MilvusServiceClient milvusServiceClient() {
        MilvusProps prop = props.getVectorstore().getMilvus();
        ConnectParam.Builder connectBuilder = ConnectParam.newBuilder()
                .withHost((String) Utils.getOrDefault(prop.getHost(), "localhost"))
                .withPort((Integer) Utils.getOrDefault(prop.getPort(), 19530))
                .withUri(prop.getUri())
                .withToken(prop.getToken())
                .withAuthorization(prop.getUsername(), prop.getPassword());
        if (prop.getCollectionName() != null) {
            connectBuilder.withDatabaseName(prop.getDatabaseName());
        }

        return new MilvusServiceClient(connectBuilder.build());
    }

}
