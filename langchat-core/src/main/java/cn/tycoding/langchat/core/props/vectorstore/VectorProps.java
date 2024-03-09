package cn.tycoding.langchat.core.props.vectorstore;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/9
 */
@Data
@ConfigurationProperties(prefix = "langchat.vectorstore")
public class VectorProps {

    private PineconeProps pinecone;
    private PgVectorProps pgvector;

}
