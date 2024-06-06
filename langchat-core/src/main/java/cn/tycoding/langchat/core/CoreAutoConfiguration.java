package cn.tycoding.langchat.core;

import cn.tycoding.langchat.common.properties.OssProps;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.chat.AzureOpenaiProps;
import cn.tycoding.langchat.core.properties.chat.GeminiProps;
import cn.tycoding.langchat.core.properties.chat.OllamaProps;
import cn.tycoding.langchat.core.properties.chat.OpenaiProps;
import cn.tycoding.langchat.core.properties.embed.AzureOpenaiEmbedProps;
import cn.tycoding.langchat.core.properties.embed.EmbeddingProps;
import cn.tycoding.langchat.core.properties.embed.OllamaEmbedProps;
import cn.tycoding.langchat.core.properties.embed.OpenaiEmbedProps;
import cn.tycoding.langchat.core.properties.image.OpenaiImageProps;
import cn.tycoding.langchat.core.properties.search.GoogleProps;
import cn.tycoding.langchat.core.properties.search.WebSearchProps;
import cn.tycoding.langchat.core.properties.vectorstore.MilvusProps;
import cn.tycoding.langchat.core.properties.vectorstore.VectorProps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        OssProps.class,
        LangChatProps.class,
        OllamaProps.class,
        OpenaiProps.class,
        AzureOpenaiProps.class,
        OpenaiImageProps.class,
        GeminiProps.class,
        EmbeddingProps.class,
        OpenaiEmbedProps.class,
        AzureOpenaiEmbedProps.class,
        OllamaEmbedProps.class,
        VectorProps.class,
        MilvusProps.class,
        WebSearchProps.class,
        GoogleProps.class,
})
@AllArgsConstructor
public class CoreAutoConfiguration {

}
