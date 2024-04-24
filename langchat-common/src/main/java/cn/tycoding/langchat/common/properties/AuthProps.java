package cn.tycoding.langchat.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Data
@ConfigurationProperties("langchat.auth")
public class AuthProps {

    /**
     * 默认忽略拦截的URL集合
     */
    private List<String> skipUrl = new ArrayList();

    /**
     * password
     */
    private String saltKey = "langchat-salt";

    /**
     * administrator account username;
     */
    private String adminName = "administrator";

    /**
     * administrator account password
     */
    private String adminPass = "123456";

    /**
     * 是否开启演示环境
     */
    private Boolean isDemoEnv = false;
}
