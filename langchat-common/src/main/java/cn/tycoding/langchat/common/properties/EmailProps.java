package cn.tycoding.langchat.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/6/11
 */
@Data
@ConfigurationProperties("langchat.auth.email")
public class EmailProps {

    private String host;
    private Integer port = 465;
    private String pass;
    private String from;
}
