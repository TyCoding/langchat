package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.biz.entity.AigcDocs;

import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/6/6
 */
public interface EmbeddingService {

    /**
     * 向量化并存储切片数据
     */
    void embedDocsSlice(AigcDocs data, String url);

    /**
     * 向量化数据搜索
     */
    List<Map<String, Object>> search(AigcDocs data);
}
