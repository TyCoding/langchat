package cn.tycoding.langchat.core.autoconfig;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.chat.OpenaiProps;
import cn.tycoding.langchat.core.properties.embed.OpenaiEmbedProps;
import cn.tycoding.langchat.core.properties.image.OpenaiImageProps;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
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
public class OpenAiAutoConfig {

    private final LangChatProps props;

    @Bean(ModelConst.OPENAI)
    @ConditionalOnProperty(value = "langchat.openai.api-key", matchIfMissing = false)
    public OpenAiStreamingChatModel openAiStreamingChatModel() {
        OpenaiProps prop = props.getOpenai();
        return OpenAiStreamingChatModel.builder()
                .baseUrl(prop.getBaseUrl())
                .apiKey(prop.getApiKey())
                .organizationId(prop.getOrganizationId())
                .modelName(prop.getModelName())
                .temperature(prop.getTemperature())
                .topP(prop.getTopP())
                .stop(prop.getStop())
                .maxTokens(prop.getMaxTokens())
                .presencePenalty(prop.getPresencePenalty())
                .frequencyPenalty(prop.getFrequencyPenalty())
                .logitBias(prop.getLogitBias())
                .responseFormat(prop.getResponseFormat())
                .seed(prop.getSeed())
                .user(prop.getUser())
                .timeout(prop.getTimeout())
                .proxy(prop.getProxy())
                .logRequests(prop.getLogRequests())
                .logResponses(prop.getLogResponses())
                .tokenizer(prop.getTokenizer())
                .build();
    }

    @Bean(ModelConst.OPENAI_TEXT)
    @ConditionalOnProperty(value = "langchat.openai.api-key", matchIfMissing = false)
    public OpenAiChatModel openAiChatModel() {
        OpenaiProps prop = props.getOpenai();
        return OpenAiChatModel.builder()
                .baseUrl(prop.getBaseUrl())
                .apiKey(prop.getApiKey())
                .organizationId(prop.getOrganizationId())
                .modelName(prop.getModelName())
                .temperature(prop.getTemperature())
                .topP(prop.getTopP())
                .stop(prop.getStop())
                .maxTokens(prop.getMaxTokens())
                .presencePenalty(prop.getPresencePenalty())
                .frequencyPenalty(prop.getFrequencyPenalty())
                .logitBias(prop.getLogitBias())
                .responseFormat(prop.getResponseFormat())
                .seed(prop.getSeed())
                .user(prop.getUser())
                .timeout(prop.getTimeout())
                .proxy(prop.getProxy())
                .logRequests(prop.getLogRequests())
                .logResponses(prop.getLogResponses())
                .tokenizer(prop.getTokenizer())
                .build();
    }

    @Bean(ModelConst.OPENAI_IMAGE)
    @ConditionalOnProperty(value = "langchat.openai.api-key", matchIfMissing = false)
    public OpenAiImageModel openAiImageModel() {
        OpenaiProps openai = props.getOpenai();
        OpenaiImageProps prop = openai.getImage();
        if (StrUtil.isBlank(prop.getApiKey())) {
            prop.setApiKey(openai.getApiKey());
        }
        if (StrUtil.isBlank(prop.getBaseUrl())) {
            prop.setBaseUrl(openai.getBaseUrl());
        }

        return OpenAiImageModel
                .builder()
                .baseUrl(prop.getBaseUrl())
                .apiKey(prop.getApiKey())
                .organizationId(prop.getOrganizationId())
                .modelName(prop.getModelName())
                .size(prop.getSize())
                .quality(prop.getQuality())
                .style(prop.getStyle())
                .user(prop.getUser())
                .responseFormat(prop.getResponseFormat())
                .timeout(prop.getTimeout())
                .maxRetries(prop.getMaxRetries())
                .proxy(prop.getProxy())
                .logRequests(prop.getLogRequests())
                .logResponses(prop.getLogResponses())
                .withPersisting(prop.getWithPersisting())
                .persistTo(prop.getPersistTo())
                .build();
    }

    @Bean(ModelConst.OPENAI_IMAGE)
    @ConditionalOnProperty(value = "langchat.openai.api-key", matchIfMissing = false)
    public OpenAiEmbeddingModel openAiEmbeddingModel() {
        OpenaiEmbedProps prop = props.getEmbedding().getOpenai();
        return OpenAiEmbeddingModel
                .builder()
                .baseUrl(prop.getBaseUrl())
                .apiKey(prop.getApiKey())
                .organizationId(prop.getOrganizationId())
                .modelName(prop.getModelName())
                .dimensions(prop.getDimensions())
                .user(prop.getUser())
                .timeout(prop.getTimeout())
                .maxRetries(prop.getMaxRetries())
                .proxy(prop.getProxy())
                .logRequests(prop.getLogRequests())
                .logResponses(prop.getLogResponses())
                .build();
    }
}
