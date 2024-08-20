package cn.tycoding.langchat.core.provider.model.config.strategy;

import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.core.consts.EmbedConst;
import dev.langchain4j.model.azure.AzureOpenAiEmbeddingModel;
import dev.langchain4j.model.azure.AzureOpenAiImageModel;
import dev.langchain4j.model.azure.AzureOpenAiStreamingChatModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GB
 * @desc
 * @since  2024-08-19 10:08
 */
@Slf4j
@Component
public class AzureOenAIModelConfigHandler implements ModelConfigHandler{
    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.AZURE_OPENAI.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        return !StrUtil.isBlank(model.getApiKey());
    }

    @Override
    public StreamingChatLanguageModel chatConfig(AigcModel model) {
        try{
            if(!whetherCurrentModel(model)){
                return null;
            }
            if(!basicCheck(model)){
                log.info("Azure OpenAi 配置信息有误");
                return null;
            }
            return AzureOpenAiStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .endpoint(model.getEndpoint())
                    .deploymentName(model.getAzureDeploymentName())
                    .maxTokens(model.getResponseLimit())
                    .temperature(model.getTemperature())
                    .logRequestsAndResponses(true)
                    .topP(model.getTopP())
                    .build();
        }catch (Exception e){
         log.error("AzureOenAI chat 配置报错",e);
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
                log.info("Azure OpenAi 配置信息有误");
                return null;
            }
            AzureOpenAiEmbeddingModel openAiEmbeddingModel = AzureOpenAiEmbeddingModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .deploymentName(model.getBaseUrl())
                    .logRequestsAndResponses(true)
                    .build();
            return Pair.of(EmbedConst.CLAZZ_NAME_AZURE_OPENAI,openAiEmbeddingModel);
        }catch (Exception e){
            log.error("AzureOenAI embedding 配置报错", e);
            return null;
        }
    }

    @Override
    public ImageModel imageConfig(AigcModel model) {
        try {
            if(!whetherCurrentModel(model)){
                return null;
            }
            if(!basicCheck(model)){
                log.info("Azure OpenAi 配置信息有误");
                return null;
            }
            return AzureOpenAiImageModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .deploymentName(model.getAzureDeploymentName())
                    .size(model.getImageSize())
                    .quality(model.getImageQuality())
                    .style(model.getImageStyle())
                    .logRequestsAndResponses(true)
                    .build();
        } catch (Exception e) {
            log.error("AzureOenAI image 配置报错", e);
            return null;
        }

    }
}
