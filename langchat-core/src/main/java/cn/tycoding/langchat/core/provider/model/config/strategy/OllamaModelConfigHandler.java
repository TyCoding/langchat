package cn.tycoding.langchat.core.provider.model.config.strategy;

import cn.hutool.core.lang.Pair;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.core.consts.EmbedConst;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author GB
 * @desc
 * @date 2024-08-19 10:08
 */
@Slf4j
@Component
public class OllamaModelConfigHandler implements ModelConfigHandler{
    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.OLLAMA.name().equals(model.getProvider());
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
                log.error("Ollama embedding 配置信息有误");
                return null;
            }
            return OllamaStreamingChatModel
                    .builder()
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .temperature(model.getTemperature())
                    .topP(model.getTopP())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
        }catch (Exception e){
            log.error("Ollama chat 配置报错", e);
            return null;
        }
    }

    @Override
    public Pair<String, DimensionAwareEmbeddingModel> embeddingConfig(AigcModel model) {
        try {
            if(!whetherCurrentModel(model)){
                return null;
            }
            if(!basicCheck(model)){
                log.error("Ollama embedding 配置信息有误");
                return null;
            }
            OllamaEmbeddingModel ollamaEmbeddingModel = OllamaEmbeddingModel
                    .builder()
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
            return Pair.of(EmbedConst.CLAZZ_NAME_OLLAMA, ollamaEmbeddingModel);
        } catch (Exception e) {
            log.error("Ollama embedding 配置报错", e);
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
                log.error("Ollama embedding 配置信息有误");
                return null;
            }
            return null;
        } catch (Exception e) {
            log.error("Ollama image 配置报错", e);
            return null;
        }
    }
}
