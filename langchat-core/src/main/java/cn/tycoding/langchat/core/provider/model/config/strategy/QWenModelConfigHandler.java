package cn.tycoding.langchat.core.provider.model.config.strategy;

import cn.hutool.core.lang.Pair;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.core.consts.EmbedConst;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GB
 * @desc
 * @date 2024-08-19 10:08
 */
@Slf4j
@Component
public class QWenModelConfigHandler implements ModelConfigHandler {
    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.Q_WEN.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        return true;
    }

    @Override
    public StreamingChatLanguageModel chatConfig(AigcModel model) {
        try {
            if (!whetherCurrentModel(model)) {
                return null;
            }
            if (!basicCheck(model)) {
                log.error("qianwen  配置信息有误");
                return null;
            }
            return QwenStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .modelName(model.getModel())
                    .baseUrl(model.getBaseUrl())
                    .maxTokens(model.getResponseLimit())
                    .temperature(Float.parseFloat(model.getTemperature().toString()))
                    .topP(model.getTopP())
                    .build();
        } catch (Exception e) {
            log.error("qian wen chat 配置报错", e);
            return null;
        }
    }

    @Override
    public Pair<String, DimensionAwareEmbeddingModel> embeddingConfig(AigcModel model) {
        try {
            if (!whetherCurrentModel(model)) {
                return null;
            }
            if (!basicCheck(model)) {
                log.error("qianwen  配置信息有误");
                return null;
            }
            QwenEmbeddingModel qwenEmbeddingModel = QwenEmbeddingModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .modelName(model.getModel())
                    .build();
            return Pair.of(EmbedConst.CLAZZ_NAME_QIANWEN, qwenEmbeddingModel);
        } catch (Exception e) {
            log.error("qian wen embedding 配置报错", e);
            return null;
        }
    }

    @Override
    public ImageModel imageConfig(AigcModel model) {
        try {
            if (!whetherCurrentModel(model)) {
                return null;
            }
            if (!basicCheck(model)) {
                log.error("qianwen  配置信息有误");
                return null;
            }
            return null;
        } catch (Exception e) {
            log.error("qian wen image 配置报错", e);
            return null;
        }

    }
}
