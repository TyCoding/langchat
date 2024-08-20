package cn.tycoding.langchat.core.provider.model.config.strategy;

import cn.hutool.core.lang.Pair;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.core.consts.EmbedConst;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.qianfan.QianfanEmbeddingModel;
import dev.langchain4j.model.qianfan.QianfanStreamingChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GB
 * @desc
 * @since 2024-08-19 10:08
 */
@Slf4j
@Component
public class QFanModelConfigHandler implements ModelConfigHandler {
    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.Q_FAN.name().equals(model.getProvider());
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
                log.error("qiafan  配置信息有误");
                return null;
            }
            return QianfanStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .secretKey(model.getSecretKey())
                    .modelName(model.getModel())
                    .baseUrl(model.getBaseUrl())
                    .temperature(model.getTemperature())
                    .topP(model.getTopP())
                    .logRequests(true)
                    .logResponses(true)
                    .build();

        } catch (Exception e) {
            log.error("Qianfan chat 配置报错", e);
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
                log.error("qiafan  配置信息有误");
                return null;
            }
            QianfanEmbeddingModel qianfanEmbeddingModel = QianfanEmbeddingModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .modelName(model.getModel())
                    .secretKey(model.getSecretKey())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
            return Pair.of(EmbedConst.CLAZZ_NAME_QIANFAN, qianfanEmbeddingModel);
        } catch (Exception e) {
            log.error("Qianfan embedding 配置报错", e);
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
                log.error("qiafan  配置信息有误");
                return null;
            }
            return null;
        } catch (Exception e) {
            log.error("Qianfan image 配置报错", e);
            return null;
        }

    }
}
