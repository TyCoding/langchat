package cn.tycoding.langchat.core.component;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.chat.OllamaProps;
import cn.tycoding.langchat.core.properties.embed.OllamaEmbedProps;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Configuration
@AllArgsConstructor
public class OllamaComponent {

    private final LangChatProps props;

    @Bean(ModelConst.OLLAMA)
    @ConditionalOnProperty(value = "langchat.ollama.base-url", matchIfMissing = false)
    public OllamaStreamingChatModel ollamaStreamingChatModel() {
        OllamaProps prop = props.getOllama();
        return OllamaStreamingChatModel.builder()
                .baseUrl(prop.getBaseUrl())
                .modelName(prop.getModelName())
                .temperature(prop.getTemperature())
                .topK(prop.getTopK())
                .topP(prop.getTopP())
                .repeatPenalty(prop.getRepeatPenalty())
                .seed(prop.getSeed())
                .numPredict(prop.getNumPredict())
                .stop(prop.getStop())
                .format(prop.getFormat())
                .timeout(prop.getTimeout())
                .build();
    }

    @Bean(ModelConst.OLLAMA_TEXT)
    @ConditionalOnProperty(value = "langchat.ollama.base-url", matchIfMissing = false)
    public OllamaChatModel ollamaChatModel() {
        OllamaProps prop = props.getOllama();
        return OllamaChatModel.builder()
                .baseUrl(prop.getBaseUrl())
                .modelName(prop.getModelName())
                .temperature(prop.getTemperature())
                .topK(prop.getTopK())
                .topP(prop.getTopP())
                .repeatPenalty(prop.getRepeatPenalty())
                .seed(prop.getSeed())
                .numPredict(prop.getNumPredict())
                .stop(prop.getStop())
                .format(prop.getFormat())
                .timeout(prop.getTimeout())
                .build();
    }

    @Bean(ModelConst.OLLAMA_EMBED)
    @ConditionalOnProperty(value = "langchat.ollama.base-url", matchIfMissing = false)
    public OllamaEmbeddingModel ollamaEmbeddingModel() {
        OllamaEmbedProps prop = props.getEmbedding().getOllama();
        OllamaProps ollama = props.getOllama();
        if (StrUtil.isBlank(prop.getBaseUrl())) {
            prop.setBaseUrl(ollama.getBaseUrl());
        }
        if (StrUtil.isBlank(prop.getModelName())) {
            prop.setModelName(ollama.getModelName());
        }
        return OllamaEmbeddingModel.builder()
                .baseUrl(prop.getBaseUrl())
                .modelName(prop.getModelName())
                .timeout(prop.getTimeout())
                .maxRetries(prop.getMaxRetries())
                .build();
    }
}
