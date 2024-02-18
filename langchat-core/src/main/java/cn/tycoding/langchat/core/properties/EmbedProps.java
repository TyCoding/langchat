package cn.tycoding.langchat.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/9
 */
@Data
@ConfigurationProperties(prefix = "langchat.embedding")
public class EmbedProps {

    private PineconeProps pinecone;
    private PgVectorProps pgvector;

}
