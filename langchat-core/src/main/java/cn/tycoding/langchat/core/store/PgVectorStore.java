package cn.tycoding.langchat.core.store;

import cn.tycoding.langchat.core.properties.PgVectorProps;
import com.pgvector.PGvector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@Component
public class PgVectorStore {

    private Connection setupConnection(PgVectorProps props) throws SQLException {
        Connection connection = DriverManager.getConnection(
                String.format("jdbc:postgresql://%s:%s/%s", props.getHost(), props.getPort(), props.getDatabase()),
                props.getUser(),
                props.getPassword()
        );
        connection.createStatement().executeUpdate("CREATE EXTENSION IF NOT EXISTS vector");
        PGvector.addVectorType(connection);
        return connection;
    }

    public void delTable(PgVectorProps props) {
        try {
            Connection connection = setupConnection(props);
            connection.createStatement().executeUpdate(String.format("DROP TABLE IF EXISTS %s", props.getTable()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
