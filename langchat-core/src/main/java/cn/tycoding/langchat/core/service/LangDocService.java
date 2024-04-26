package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import dev.langchain4j.service.TokenStream;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/4
 */
public interface LangDocService {

    /**
     * 解析文本向量
     */
    EmbeddingR embeddingText(DocR req);

    /**
     * 解析文本文件向量
     */
    List<EmbeddingR> embeddingDocs(DocR req);

    /**
     * 解析结构化文件向量
     */
    void embeddingExcel(DocR req);

    TokenStream search(DocR req);

}
