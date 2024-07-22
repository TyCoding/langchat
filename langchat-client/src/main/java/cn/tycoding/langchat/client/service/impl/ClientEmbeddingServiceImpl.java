package cn.tycoding.langchat.client.service.impl;

import cn.tycoding.langchat.client.service.ClientEmbeddingService;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.core.service.LangDocService;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static cn.tycoding.langchat.core.consts.EmbedConst.KNOWLEDGE;
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

/**
 * @author tycoding
 * @since 2024/6/6
 */
@Slf4j
@Service
@AllArgsConstructor
public class ClientEmbeddingServiceImpl implements ClientEmbeddingService {

    private final LangDocService langDocService;
    private final PgVectorEmbeddingStore embeddingStore;

    @Async
    @Override
    public void embedDocs(ChatReq data) {
        langDocService.embeddingDocs(data);
    }

    @Override
    public void deleteVector(String knowledgeId) {
        Filter filter = metadataKey(KNOWLEDGE).isEqualTo(knowledgeId);
        embeddingStore.removeAll(filter);
    }
}
