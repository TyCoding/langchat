package cn.tycoding.langchat.aigc.service.impl;

import static cn.tycoding.langchat.core.consts.EmbedConst.KNOWLEDGE;
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

import cn.tycoding.langchat.aigc.entity.AigcDocs;
import cn.tycoding.langchat.aigc.entity.AigcDocsSlice;
import cn.tycoding.langchat.aigc.service.AigcKnowledgeService;
import cn.tycoding.langchat.aigc.service.EmbeddingService;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import cn.tycoding.langchat.core.provider.EmbedProvider;
import cn.tycoding.langchat.core.service.LangDocService;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.embedding.milvus.MilvusEmbeddingStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/6/6
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmbeddingServiceImpl implements EmbeddingService {

    private final EmbedProvider embedProvider;
    private final LangDocService langDocService;
    private final AigcKnowledgeService aigcKnowledgeService;
    private final MilvusEmbeddingStore embeddingStore;

    @Async
    @Override
    public void embedDocs(ChatReq data) {
        langDocService.embeddingDocs(data);
    }

    @Async
    @Override
    public void embedDocsSlice(AigcDocs data, String path) {
        List<EmbeddingR> list = langDocService.embeddingDocs(
                new ChatReq()
                        .setDocsName(data.getName())
                        .setKnowledgeId(data.getKnowledgeId())
                        .setPath(path));
        list.forEach(i -> {
            aigcKnowledgeService.addDocsSlice(new AigcDocsSlice()
                    .setKnowledgeId(data.getKnowledgeId())
                    .setDocsId(data.getId())
                    .setVectorId(i.getVectorId())
                    .setName(data.getName())
                    .setContent(i.getText())
            );
        });

        aigcKnowledgeService.updateDocs(new AigcDocs().setId(data.getId()).setSliceStatus(true).setSliceNum(list.size()));
    }

    @Override
    public List<Map<String, Object>> search(AigcDocs data) {
        EmbeddingModel embeddingModel = embedProvider.embed();
        Embedding queryEmbedding = embeddingModel.embed(data.getContent()).content();
        Filter filter = metadataKey(KNOWLEDGE).isEqualTo(data.getKnowledgeId());
        EmbeddingSearchResult<TextSegment> list = embeddingStore.search(EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .filter(filter)
                .build());

        List<Map<String, Object>> result = new ArrayList<>();
        list.matches().forEach(i -> {
            TextSegment embedded = i.embedded();
            Map<String, Object> map = embedded.metadata().toMap();
            map.put("text", embedded.text());
            result.add(map);
        });
        return result;
    }

    @Override
    public void deleteVector(String knowledgeId) {
        Filter filter = metadataKey(KNOWLEDGE).isEqualTo(knowledgeId);
        embeddingStore.removeAll(filter);
    }
}
