package cn.tycoding.langchat.server.component;

import cn.tycoding.langchat.server.properties.AuthProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2023/11/15
 */
@Configuration
@EnableConfigurationProperties({AuthProps.class})
public class AuthAutoConfiguration {

}
