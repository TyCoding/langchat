package cn.tycoding.langchat.core.props.vectorstore;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2023/12/9
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "langchat.vectorstore.pgvector")
public class PgVectorProps {

    private String host;
    private Integer port;
    private String user;
    private String password;
    private String database;
    private String table;
    private Integer dimension;
    private Boolean useIndex;
    private Integer indexListSize;
    private Boolean createTable;
    private Boolean dropTableFirst;
}
