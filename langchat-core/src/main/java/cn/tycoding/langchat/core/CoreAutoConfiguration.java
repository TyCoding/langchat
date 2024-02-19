package cn.tycoding.langchat.core;

import cn.tycoding.langchat.core.properties.*;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2023/11/15
 */
@Configuration
@EnableConfigurationProperties({OssProps.class, LangChatProps.class, EmbedProps.class,
        OllamaProps.class,
        GeminiProps.class,
        PgVectorProps.class, PineconeProps.class})
public class CoreAutoConfiguration {

}
