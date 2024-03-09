package cn.tycoding.langchat.core.autoconfig;

import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.props.LangChatProps;
import cn.tycoding.langchat.core.props.chat.QianfanProps;
import dev.langchain4j.model.qianfan.QianfanChatModel;
import dev.langchain4j.model.qianfan.QianfanStreamingChatModel;
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
public class QianfanAutoConfig {

    private final LangChatProps props;

    @Bean(ModelConst.QIANFAN)
    @ConditionalOnProperty(value = "langchat.qianfan.api-key", matchIfMissing = false)
    public QianfanStreamingChatModel qianfanStreamingChatModel() {
        QianfanProps prop = props.getQianfan();
        return QianfanStreamingChatModel.builder()
                .baseUrl(prop.getBaseUrl())
                .apiKey(prop.getApiKey())
                .secretKey(prop.getSecretKey())
                .temperature(prop.getTemperature())
                .topP(prop.getTopP())
                .modelName(prop.getModelName())
                .endpoint(prop.getEndpoint())
                .responseFormat(prop.getResponseFormat())
                .penaltyScore(prop.getPenaltyScore())
                .logRequests(prop.getLogRequests())
                .logResponses(prop.getLogResponses())
                .build();
    }

    @Bean(ModelConst.QIANFAN_TEXT)
    @ConditionalOnProperty(value = "langchat.qianfan.api-key", matchIfMissing = false)
    public QianfanChatModel qianfanChatModel() {
        QianfanProps prop = props.getQianfan();
        return QianfanChatModel.builder()
                .baseUrl(prop.getBaseUrl())
                .apiKey(prop.getApiKey())
                .secretKey(prop.getSecretKey())
                .temperature(prop.getTemperature())
                .topP(prop.getTopP())
                .modelName(prop.getModelName())
                .endpoint(prop.getEndpoint())
                .responseFormat(prop.getResponseFormat())
                .penaltyScore(prop.getPenaltyScore())
                .logRequests(prop.getLogRequests())
                .logResponses(prop.getLogResponses())
                .build();
    }
}
