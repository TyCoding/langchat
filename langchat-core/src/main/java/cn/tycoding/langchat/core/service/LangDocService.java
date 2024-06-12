package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.common.dto.ChatReq;
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
    EmbeddingR embeddingText(ChatReq req);

    /**
     * 解析文本文件向量
     */
    List<EmbeddingR> embeddingDocs(ChatReq req);

    /**
     * 文档对话
     */
    TokenStream chat(ChatReq req);
}
