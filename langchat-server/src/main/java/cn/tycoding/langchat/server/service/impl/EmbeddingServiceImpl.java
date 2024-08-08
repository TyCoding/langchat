/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.server.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.entity.AigcDocs;
import cn.tycoding.langchat.biz.entity.AigcDocsSlice;
import cn.tycoding.langchat.biz.service.AigcKnowledgeService;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import cn.tycoding.langchat.core.provider.EmbeddingProvider;
import cn.tycoding.langchat.core.service.LangEmbeddingService;
import cn.tycoding.langchat.server.service.EmbeddingService;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.tycoding.langchat.core.consts.EmbedConst.KNOWLEDGE;
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

/**
 * @author tycoding
 * @since 2024/6/6
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmbeddingServiceImpl implements EmbeddingService {

    private final EmbeddingProvider embeddingProvider;
    private final LangEmbeddingService langEmbeddingService;
    private final AigcKnowledgeService aigcKnowledgeService;
    private final PgVectorEmbeddingStore embeddingStore;

    @Async
    @Override
    public void embedDocsSlice(AigcDocs data, String url) {
        List<EmbeddingR> list = langEmbeddingService.embeddingDocs(
                new ChatReq()
                        .setDocsName(data.getName())
                        .setKnowledgeId(data.getKnowledgeId())
                        .setUrl(url));
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
        if (StrUtil.isBlank(data.getKnowledgeId()) || StrUtil.isBlank(data.getContent())) {
            return List.of();
        }

        EmbeddingModel embeddingModel = embeddingProvider.embed();
        Embedding queryEmbedding = embeddingModel.embed(data.getContent()).content();
        Filter filter = metadataKey(KNOWLEDGE).isEqualTo(data.getKnowledgeId());
        EmbeddingSearchResult<TextSegment> list = embeddingStore.search(EmbeddingSearchRequest
                .builder()
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
}
