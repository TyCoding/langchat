package cn.tycoding.langchat.core.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/9
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "langchat.embedding.pgvector")
public class PgVectorProps {

    private String host;
    private Integer port;
    private String user;
    private String password;
    private String database;
    private String table;
}
