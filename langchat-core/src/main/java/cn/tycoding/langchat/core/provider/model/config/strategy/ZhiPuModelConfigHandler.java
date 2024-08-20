package cn.tycoding.langchat.core.provider.model.config.strategy;

import cn.hutool.core.lang.Pair;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.core.consts.EmbedConst;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.zhipu.ZhipuAiEmbeddingModel;
import dev.langchain4j.model.zhipu.ZhipuAiImageModel;
import dev.langchain4j.model.zhipu.ZhipuAiStreamingChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GB
 * @desc
 * @since 2024-08-19
 */
@Slf4j
@Component
public class ZhiPuModelConfigHandler implements ModelConfigHandler{
    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.ZHIPU.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        return true;
    }

    @Override
    public StreamingChatLanguageModel chatConfig(AigcModel model) {
        try{
            if(!whetherCurrentModel(model)){
                return null;
            }
            if(!basicCheck(model)){
                log.error("zhipu 配置信息有误");
                return null;
            }
            return ZhipuAiStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .model(model.getModel())
                    .maxToken(model.getResponseLimit())
                    .temperature(model.getTemperature())
                    .topP(model.getTopP())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
        }catch(Exception e){
            log.error("zhipu chat 配置报错", e);
            return null;
        }

    }

    @Override
    public Pair<String, DimensionAwareEmbeddingModel> embeddingConfig(AigcModel model) {
        try{
            if(!whetherCurrentModel(model)){
                return null;
            }
            if(!basicCheck(model)){
                log.error("zhipu 配置信息有误");
                return null;
            }
            ZhipuAiEmbeddingModel zhipuAiEmbeddingModel = ZhipuAiEmbeddingModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .model(model.getModel())
                    .baseUrl(model.getBaseUrl())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
            return Pair.of(EmbedConst.CLAZZ_NAME_ZHIPU, zhipuAiEmbeddingModel);
        }catch (Exception e){
            log.error("zhipu embedding 配置报错", e);
            return null;
        }
    }

    @Override
    public ImageModel imageConfig(AigcModel model) {
        try{
            if(!whetherCurrentModel(model)){
                return null;
            }
            if(!basicCheck(model)){
                log.error("zhipu 配置信息有误");
                return null;
            }
           return  ZhipuAiImageModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .model(model.getModel())
                    .baseUrl(model.getBaseUrl())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
        }catch (Exception e){
            log.error("zhipu image 配置报错", e);
            return null;
        }
    }
}
