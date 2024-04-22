package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.vectorstore.MilvusProps;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
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

}
