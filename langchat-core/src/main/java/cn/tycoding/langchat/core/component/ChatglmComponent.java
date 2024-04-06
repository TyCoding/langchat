package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.chat.ChatglmProps;
import dev.langchain4j.model.qianfan.QianfanChatModel;
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
public class ChatglmComponent {

    private final LangChatProps props;

    @Bean(ModelConst.CHATGLM_TEXT)
    @ConditionalOnProperty(value = "langchat.chatglm.api-key", matchIfMissing = false)
    public QianfanChatModel qianfanChatModel() {
        ChatglmProps prop = props.getChatglm();
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
