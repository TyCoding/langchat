package cn.tycoding.langchat.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Data
@ConfigurationProperties("langchat.auth")
public class AuthProps {

    /**
     * password
     */
    private String saltKey = "langchat-salt";

    /**
     * administrator account username;
     */
    private String adminName;

    /**
     * administrator account password
     */
    private String adminPass;
}
