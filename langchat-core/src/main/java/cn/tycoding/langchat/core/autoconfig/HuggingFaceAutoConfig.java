package cn.tycoding.langchat.core.autoconfig;

import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.props.LangChatProps;
import cn.tycoding.langchat.core.props.chat.HuggingfaceProps;
import dev.langchain4j.model.huggingface.HuggingFaceChatModel;
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
public class HuggingFaceAutoConfig {

    private final LangChatProps props;

    @Bean(ModelConst.QIANFAN_TEXT)
    @ConditionalOnProperty(value = "langchat.huggingface.access-token", matchIfMissing = false)
    public HuggingFaceChatModel huggingFaceChatModel() {
        HuggingfaceProps prop = props.getHuggingface();
        return HuggingFaceChatModel.builder()
                .accessToken(prop.getAccessToken())
                .modelId(prop.getModelId())
                .timeout(prop.getTimeout())
                .temperature(prop.getTemperature())
                .maxNewTokens(prop.getMaxNewTokens())
                .returnFullText(prop.getReturnFullText())
                .waitForModel(prop.getWaitForModel())
                .build();
    }
}
