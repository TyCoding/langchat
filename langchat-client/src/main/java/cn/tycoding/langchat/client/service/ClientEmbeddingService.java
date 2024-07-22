package cn.tycoding.langchat.client.service;

import cn.tycoding.langchat.common.dto.ChatReq;

/**
 * @author tycoding
 * @since 2024/6/6
 */
public interface ClientEmbeddingService {

    /**
     * 只向量化文档，不存储切片数据
     */
    void embedDocs(ChatReq data);

    /**
     * 删除vector store
     */
    void deleteVector(String knowledgeId);
}
