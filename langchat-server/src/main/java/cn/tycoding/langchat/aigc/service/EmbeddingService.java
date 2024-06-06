package cn.tycoding.langchat.aigc.service;

import cn.tycoding.langchat.aigc.entity.AigcDocs;

import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/6/6
 */
public interface EmbeddingService {

    void embedDocsSlice(AigcDocs data, String path);

    List<Map<String, Object>> search(AigcDocs data);
}
