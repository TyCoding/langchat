package cn.tycoding.langchat.auth;

import cn.tycoding.langchat.common.properties.AuthProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Auth 配置注入
 *
 * @author tycoding
 * @since 2024/1/5
 */
@Configuration
@EnableConfigurationProperties({AuthProps.class})
public class AuthAutoConfiguration {

}
