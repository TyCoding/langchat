package cn.tycoding.langchat.core.provider.model.config.strategy;

import cn.hutool.core.lang.Pair;
import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import dev.langchain4j.model.anthropic.AnthropicStreamingChatModel;
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
public class ClaudeModelConfigHandler implements ModelConfigHandler{
    @Override
    public boolean whetherCurrentModel(AigcModel model) {
        return ProviderEnum.CLAUDE.name().equals(model.getProvider());
    }

    @Override
    public boolean basicCheck(AigcModel model) {
        String baseUrl = model.getBaseUrl();
        if (baseUrl == null) {
            log.error("Base URL 为空");
            return false;
        }
        if (!baseUrl.endsWith("/")) {
            model.setBaseUrl(baseUrl + "/");
        }
        return true;
    }

    @Override
    public StreamingChatLanguageModel chatConfig(AigcModel model) {
        try{
            if(!whetherCurrentModel(model)){
                return null;
            }
            if(!basicCheck(model)){
                log.error("claude 配置信息有误");
                return null;
            }
            return AnthropicStreamingChatModel
                    .builder()
                    .apiKey(model.getApiKey())
                    .baseUrl(model.getBaseUrl())
                    .modelName(model.getModel())
                    .temperature(model.getTemperature())
                    .topP(model.getTopP())
                    .logRequests(true)
                    .logResponses(true)
                    .build();
        }catch(Exception e){
            log.error("Claude chat 配置报错", e);
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
                log.error("claude 配置信息有误");
                return null;
            }
            return null;
        }catch (Exception e){
            log.error("Claude embedding 配置报错", e);
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
                log.error("claude 配置信息有误");
                return null;
            }
            return null;
        }catch (Exception e){
            log.error("Claude image 配置报错", e);
            return null;
        }

    }
}
