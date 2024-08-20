package cn.tycoding.langchat.core.provider.model.config.strategy;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.vertexai.VertexAiGeminiStreamingChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GB
 * @desc
 * @date 2024-08-19 10:08
 */
@Slf4j
@Component
public class GeminiModelConfigHandler implements ModelConfigHandler {
    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.GEMINI.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        return !StrUtil.isBlank(model.getApiKey()) && !StrUtil.isBlank(model.getSecretKey());
    }

    @Override
    public StreamingChatLanguageModel chatConfig(AigcModel model) {
        try {
            if(!whetherCurrentModel(model)){
                return null;
            }
            if (!basicCheck(model)) {
                log.info("Gemini 配置信息有误，无法加载");
                return null;
            }
            return VertexAiGeminiStreamingChatModel
                    .builder()
                    .project(model.getGeminiProject())
                    .location(model.getGeminiLocation())
                    .modelName(model.getModel())
                    .temperature(Float.parseFloat(model.getTemperature().toString()))
                    .maxOutputTokens(model.getResponseLimit())
                    .logRequests(true)
                    .logResponses(true)
                    .topP(Float.parseFloat(model.getTopP().toString()))
                    .build();
        } catch (Exception e) {
            log.error("Gemini 配置报错", e);
            return null;
        }
    }

    @Override
    public Pair<String, DimensionAwareEmbeddingModel> embeddingConfig(AigcModel model) {
        try {
            if(!whetherCurrentModel(model)){
                return null;
            }
            if (!basicCheck(model)) {
                log.info("Gemini 配置信息有误，无法加载");
                return null;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public ImageModel imageConfig(AigcModel model) {
        try {
            if(!whetherCurrentModel(model)){
                return null;
            }
            if (!basicCheck(model)) {
                log.info("Gemini 配置信息有误，无法加载");
                return null;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }
}
