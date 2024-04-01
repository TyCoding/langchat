package cn.tycoding.langchat.core.autoconfig;

import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.vectorstore.PgVectorProps;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
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
public class VectorAutoConfig {

    private final LangChatProps props;

    @Bean(ModelConst.PGVECTOR)
    @ConditionalOnProperty(value = "langchat.pgvector.host", matchIfMissing = false)
    public PgVectorEmbeddingStore pgVectorEmbeddingStore() {
        PgVectorProps prop = props.getVectorstore().getPgvector();
        return PgVectorEmbeddingStore.builder()
                .host(prop.getHost())
                .port(prop.getPort())
                .user(prop.getUser())
                .password(prop.getPassword())
                .database(prop.getDatabase())
                .table(prop.getTable())
                .dimension(prop.getDimension())
                .useIndex(prop.getUseIndex())
                .indexListSize(prop.getIndexListSize())
                .createTable(prop.getCreateTable())
                .dropTableFirst(prop.getDropTableFirst())
                .build();
    }

}
