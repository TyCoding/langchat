package cn.tycoding.langchat.core.provider.model.config.strategy;

import cn.hutool.core.lang.Pair;
import cn.tycoding.langchat.biz.entity.AigcModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.DimensionAwareEmbeddingModel;
import dev.langchain4j.model.image.ImageModel;

/**
 * @author GB
 * @desc 模型装配策略
 * @since 2024-08-18 09:57
 */
public interface ModelConfigHandler {
    /**
     * 判断是不是当前模型
     * @param model
     * @return
     */
    boolean whetherCurrentModel(AigcModel model);

    /**
     * 基本校验
     * @param model
     * @return
     */
    boolean basicCheck(AigcModel model);


    /**
     * streaming chat config
     *
     * @param model
     * @return
     */
    StreamingChatLanguageModel buildStreamingChat(AigcModel model);

    ChatLanguageModel buildChatLanguageModel(AigcModel model);

    /**
     * embedding config
     *
     * @param model
     * @return
     */
    Pair<String, DimensionAwareEmbeddingModel> buildEmbedding(AigcModel model);

    /**
     * image config
     *
     * @param model
     * @return
     */
    ImageModel buildImage(AigcModel model);


}
