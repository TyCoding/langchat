package cn.tycoding.langchat.common;

import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.properties.EmailProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Configuration
@EnableConfigurationProperties({AuthProps.class, EmailProps.class})
public class CommonAutoConfiguration {

}
