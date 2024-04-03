package cn.tycoding.langchat.core.autoconfig;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.core.properties.LangChatProps;
import cn.tycoding.langchat.core.properties.chat.GeminiProps;
import dev.langchain4j.model.vertexai.VertexAiGeminiChatModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiStreamingChatModel;
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
public class GeminiAutoConfig {

    private final LangChatProps props;

    @Bean(ModelConst.GEMINI)
    @ConditionalOnProperty(value = "langchat.gemini.project", matchIfMissing = false)
    public VertexAiGeminiStreamingChatModel vertexAiGeminiStreamingChatModel() {
        GeminiProps prop = props.getGemini();
        return VertexAiGeminiStreamingChatModel.builder()
                .project(prop.getProject())
                .project(prop.getProject())
                .location(prop.getLocation())
                .modelName(prop.getModelName())
                .temperature(prop.getTemperature())
                .maxOutputTokens(prop.getMaxOutputTokens())
                .topK(prop.getTopK())
                .topP(prop.getTopP())
                .build();
    }

    @Bean(ModelConst.GEMINI_TEXT)
    @ConditionalOnProperty(value = "langchat.gemini.project", matchIfMissing = false)
    public VertexAiGeminiChatModel vertexAiGeminiChatModel() {
        GeminiProps prop = props.getGemini();
        return VertexAiGeminiChatModel.builder()
                .project(prop.getProject())
                .project(prop.getProject())
                .location(prop.getLocation())
                .modelName(prop.getModelName())
                .temperature(prop.getTemperature())
                .maxOutputTokens(prop.getMaxOutputTokens())
                .topK(prop.getTopK())
                .topP(prop.getTopP())
                .build();
    }

    @Bean(ModelConst.GEMINI_IMAGE)
    @ConditionalOnProperty(value = "langchat.gemini.project", matchIfMissing = false)
    public VertexAiGeminiChatModel vertexAiGeminiImageChatModel() {
        GeminiProps prop = props.getGemini();
        return VertexAiGeminiChatModel.builder()
                .project(prop.getProject())
                .project(prop.getProject())
                .location(prop.getLocation())
                .modelName(StrUtil.isNotBlank(prop.getModelName()) ? prop.getModelName() : "gemini-pro-vision")
                .temperature(prop.getTemperature())
                .maxOutputTokens(prop.getMaxOutputTokens())
                .topK(prop.getTopK())
                .topP(prop.getTopP())
                .build();
    }
}
