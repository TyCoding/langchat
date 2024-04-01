package cn.tycoding.langchat.core;

import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.chat.AzureOpenaiProps;
import cn.tycoding.langchat.core.properties.chat.ChatglmProps;
import cn.tycoding.langchat.core.properties.chat.GeminiProps;
import cn.tycoding.langchat.core.properties.chat.OllamaProps;
import cn.tycoding.langchat.core.properties.chat.OpenaiProps;
import cn.tycoding.langchat.core.properties.chat.QianfanProps;
import cn.tycoding.langchat.core.properties.embed.AzureOpenaiEmbedProps;
import cn.tycoding.langchat.core.properties.embed.EmbeddingProps;
import cn.tycoding.langchat.core.properties.embed.OllamaEmbedProps;
import cn.tycoding.langchat.core.properties.embed.OpenaiEmbedProps;
import cn.tycoding.langchat.common.properties.OssProps;
import cn.tycoding.langchat.core.properties.image.OpenaiImageProps;
import cn.tycoding.langchat.core.properties.vectorstore.PgVectorProps;
import cn.tycoding.langchat.core.properties.vectorstore.PineconeProps;
import cn.tycoding.langchat.core.properties.vectorstore.VectorProps;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2023/11/15
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        OssProps.class,
        LangChatProps.class,
        VectorProps.class,
        OllamaProps.class,
        OpenaiProps.class,
        AzureOpenaiProps.class,
        QianfanProps.class,
        ChatglmProps.class,
        OpenaiImageProps.class,
        GeminiProps.class,
        EmbeddingProps.class,
        OpenaiEmbedProps.class,
        AzureOpenaiEmbedProps.class,
        OllamaEmbedProps.class,
        PgVectorProps.class,
        PineconeProps.class
})
@AllArgsConstructor
public class CoreAutoConfiguration {

}
