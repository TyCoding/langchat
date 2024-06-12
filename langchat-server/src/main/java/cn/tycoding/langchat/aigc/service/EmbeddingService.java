package cn.tycoding.langchat.aigc.service;

import cn.tycoding.langchat.aigc.entity.AigcDocs;

import cn.tycoding.langchat.common.dto.ChatReq;
import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/6/6
 */
public interface EmbeddingService {

    /**
     * 只向量化文档，不存储切片数据
     */
    void embedDocs(ChatReq data);

    /**
     * 向量化并存储切片数据
     */
    void embedDocsSlice(AigcDocs data, String path);

    /**
     * 向量化数据搜索
     */
    List<Map<String, Object>> search(AigcDocs data);

    /**
     * 删除vector store
     */
    void deleteVector(String knowledgeId);
}
