package cn.tycoding.langchat.aigc.service;

import cn.tycoding.langchat.aigc.entity.AigcModel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/19
 */
public interface AigcModelService extends IService<AigcModel> {

    /**
     * Get a list of chat models
     */
    List<AigcModel> getChatModels();

    /**
     * Gets a list of models for the Vincennes diagram
     */
    List<AigcModel> getImageModels();

    /**
     * Gets the list of Embedding models
     */
    List<AigcModel> getEmbeddingModels();
}

