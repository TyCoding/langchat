package cn.tycoding.langchat.core;

import cn.tycoding.langchat.core.properties.EmbedProps;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.OllamaProps;
import cn.tycoding.langchat.core.properties.OssProps;
import cn.tycoding.langchat.core.properties.PgVectorProps;
import cn.tycoding.langchat.core.properties.PineconeProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2023/11/15
 */
@Configuration
@EnableConfigurationProperties({OssProps.class, LangChatProps.class, EmbedProps.class,
        OllamaProps.class,
        PgVectorProps.class, PineconeProps.class})
public class CoreAutoConfiguration {

}
