package cn.tycoding.langchat.core.provider.model.config.strategy;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.core.consts.EmbedConst;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiImageModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author GB
 * @desc
 * @since 2024-08-19 10:08
 */
@Slf4j
public class OenAIModelConfigHandler implements ModelConfigHandler{
    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.OPENAI.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        String apiKey = model.getApiKey();
        if (StrUtil.isBlank(apiKey)) {
            log.error("openai apikey is null");
            return false;
        }
        return true;
    }

    @Override
    public StreamingChatLanguageModel chatConfig(AigcModel model) {
        try {
            if(!whetherCurrentModel(model)){
                return null;
            }
            if (!basicCheck(model)) {
                log.error("openai 配置信息有误");
                return null;
            }
            return OpenAiStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .maxTokens(model.getResponseLimit())
                    .temperature(model.getTemperature())
                    .logRequests(true)
                    .logResponses(true)
                    .topP(model.getTopP())
                    .build();
        } catch (Exception e) {
            log.error("openai chat 模型配置报错", e);
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
                log.error("openai 配置信息有误");
                return null;
            }
            OpenAiEmbeddingModel openAiEmbeddingModel = OpenAiEmbeddingModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .dimensions(model.getDimensions())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
            return Pair.of(EmbedConst.CLAZZ_NAME_OPENAI,openAiEmbeddingModel);
        } catch (Exception e) {
            log.error("openai embedding 模型配置报错", e);
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
                log.error("openai 配置信息有误");
                return null;
            }
            return OpenAiImageModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .size(model.getImageSize())
                    .quality(model.getImageQuality())
                    .style(model.getImageStyle())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
        } catch (Exception e) {
            log.error("openai image 模型配置报错", e);
            return null;
        }


    }
}
