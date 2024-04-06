package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.chat.AzureOpenaiProps;
import cn.tycoding.langchat.core.properties.embed.AzureOpenaiEmbedProps;
import dev.langchain4j.model.azure.AzureOpenAiChatModel;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.azure.AzureOpenAiStreamingChatModel;
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
public class AzureOpenaiComponent {

    private final LangChatProps props;

    @Bean(ModelConst.AZUREOPENAI)
    @ConditionalOnProperty(value = "langchat.azureopenai.api-key", matchIfMissing = false)
    public AzureOpenAiStreamingChatModel azureOpenAiStreamingChatModel() {
        AzureOpenaiProps prop = props.getAzureOpenai();
        return AzureOpenAiStreamingChatModel.builder()
                .endpoint(prop.getEndpoint())
                .serviceVersion(prop.getServiceVersion())
                .apiKey(prop.getApiKey())
                .deploymentName(prop.getDeploymentName())
                .tokenizer(prop.getTokenizer())
                .maxTokens(prop.getMaxTokens())
                .temperature(prop.getTemperature())
                .topP(prop.getTopP())
                .user(prop.getUser())
                .n(prop.getN())
                .stop(prop.getStop())
                .presencePenalty(prop.getPresencePenalty())
                .frequencyPenalty(prop.getFrequencyPenalty())
                .timeout(prop.getTimeout())
                .seed(prop.getSeed())
                .maxRetries(prop.getMaxRetries())
                .proxyOptions(prop.getProxyOptions())
                .logRequestsAndResponses(prop.isLogRequestsAndResponses())
                .build();
    }

    @Bean(ModelConst.OPENAI_TEXT)
    @ConditionalOnProperty(value = "langchat.azureopenai.api-key", matchIfMissing = false)
    public AzureOpenAiChatModel openAiChatModel() {
        AzureOpenaiProps prop = props.getAzureOpenai();
        return AzureOpenAiChatModel.builder()
                .endpoint(prop.getEndpoint())
                .serviceVersion(prop.getServiceVersion())
                .apiKey(prop.getApiKey())
                .deploymentName(prop.getDeploymentName())
                .tokenizer(prop.getTokenizer())
                .maxTokens(prop.getMaxTokens())
                .temperature(prop.getTemperature())
                .topP(prop.getTopP())
                .user(prop.getUser())
                .n(prop.getN())
                .stop(prop.getStop())
                .presencePenalty(prop.getPresencePenalty())
                .frequencyPenalty(prop.getFrequencyPenalty())
                .timeout(prop.getTimeout())
                .seed(prop.getSeed())
                .maxRetries(prop.getMaxRetries())
                .proxyOptions(prop.getProxyOptions())
                .logRequestsAndResponses(prop.isLogRequestsAndResponses())
                .build();
    }

    @Bean(ModelConst.AZUREOPENAI_EMBED)
    @ConditionalOnProperty(value = "langchat.azureopenai.api-key", matchIfMissing = false)
    public AzureOpenAiEmbeddingModel azureOpenAiEmbeddingModel() {
        AzureOpenaiEmbedProps prop = props.getEmbedding().getAzureopenai();
        return AzureOpenAiEmbeddingModel.builder()
                .endpoint(prop.getEndpoint())
                .serviceVersion(prop.getServiceVersion())
                .apiKey(prop.getApiKey())
                .deploymentName(prop.getDeploymentName())
                .timeout(prop.getTimeout())
                .maxRetries(prop.getMaxRetries())
                .proxyOptions(prop.getProxyOptions())
                .logRequestsAndResponses(prop.isLogRequestsAndResponses())
                .build();
    }
}
