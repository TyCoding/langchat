package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.properties.LangChatProps;
import com.alibaba.fastjson2.JSON;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiStreamingChatModel;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.tycoding.langchat.core.consts.ModelConst.*;
import static cn.tycoding.langchat.core.consts.PropConst.GEMINI_CON;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Configuration
@AllArgsConstructor
public class GeminiComponent {

    private final LangChatProps props;

    @Bean(GEMINI_F)
    @ConditionalOnProperty(GEMINI_CON)
    public VertexAiGeminiStreamingChatModel vertexAiGeminiStreamingChatModel_f() {
        VertexAiGeminiStreamingChatModel.VertexAiGeminiStreamingChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), VertexAiGeminiStreamingChatModel.VertexAiGeminiStreamingChatModelBuilder.class);
        builder.modelName(GEMINI_F);
        return builder.build();
    }

    @Bean(GEMINI_P)
    @ConditionalOnProperty(GEMINI_CON)
    public VertexAiGeminiStreamingChatModel vertexAiGeminiStreamingChatModel_p() {
        VertexAiGeminiStreamingChatModel.VertexAiGeminiStreamingChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), VertexAiGeminiStreamingChatModel.VertexAiGeminiStreamingChatModelBuilder.class);
        builder.modelName(GEMINI_P);
        return builder.build();
    }

    @Bean(GEMINI_F + TEXT_SUFFIX)
    @ConditionalOnProperty(GEMINI_CON)
    public VertexAiGeminiChatModel vertexAiGeminiChatModel_f() {
        VertexAiGeminiChatModel.VertexAiGeminiChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), VertexAiGeminiChatModel.VertexAiGeminiChatModelBuilder.class);
        builder.modelName(GEMINI_F);
        return builder.build();
    }

    @Bean(GEMINI_P + TEXT_SUFFIX)
    @ConditionalOnProperty(GEMINI_CON)
    public VertexAiGeminiChatModel vertexAiGeminiChatModel_p() {
        VertexAiGeminiChatModel.VertexAiGeminiChatModelBuilder builder =
                JSON.parseObject(JSON.toJSONString(props.getOpenai()), VertexAiGeminiChatModel.VertexAiGeminiChatModelBuilder.class);
        builder.modelName(GEMINI_P);
        return builder.build();
    }
}
