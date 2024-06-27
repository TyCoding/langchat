package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.vectorstore.PgvectorProps;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.AllArgsConstructor;
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
    public PgVectorEmbeddingStore pgVectorEmbeddingStore() {
        PgvectorProps prop = props.getVectorstore().getPgvector();
        return PgVectorEmbeddingStore.builder()
                .host(prop.getHost())
                .port(prop.getPort())
                .database(prop.getDatabase())
                .dimension(prop.getDimension())
                .user(prop.getUser())
                .password(prop.getPassword())
                .table(prop.getTable())
                .useIndex(prop.getUseIndex())
                .createTable(prop.getCreateTable())
                .dropTableFirst(prop.getDropTableFirst())
                .build();
    }
}
