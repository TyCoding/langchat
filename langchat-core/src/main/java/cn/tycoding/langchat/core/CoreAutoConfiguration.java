package cn.tycoding.langchat.core;

import cn.tycoding.langchat.core.props.LangChatProps;
import cn.tycoding.langchat.core.props.chat.AzureOpenaiProps;
import cn.tycoding.langchat.core.props.chat.ChatglmProps;
import cn.tycoding.langchat.core.props.chat.GeminiProps;
import cn.tycoding.langchat.core.props.chat.OllamaProps;
import cn.tycoding.langchat.core.props.chat.OpenaiProps;
import cn.tycoding.langchat.core.props.chat.QianfanProps;
import cn.tycoding.langchat.core.props.embed.AzureOpenaiEmbedProps;
import cn.tycoding.langchat.core.props.embed.EmbeddingProps;
import cn.tycoding.langchat.core.props.embed.OllamaEmbedProps;
import cn.tycoding.langchat.core.props.embed.OpenaiEmbedProps;
import cn.tycoding.langchat.core.props.file.OssProps;
import cn.tycoding.langchat.core.props.image.OpenaiImageProps;
import cn.tycoding.langchat.core.props.vectorstore.PgVectorProps;
import cn.tycoding.langchat.core.props.vectorstore.PineconeProps;
import cn.tycoding.langchat.core.props.vectorstore.VectorProps;
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
