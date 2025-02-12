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

package cn.tycoding.langchat.ai.core.provider;

import cn.tycoding.langchat.ai.biz.entity.AigcKnowledge;
import cn.tycoding.langchat.common.core.exception.ServiceException;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Slf4j
@Component
@AllArgsConstructor
public class EmbeddingProvider {

    private final EmbeddingStoreFactory embeddingStoreFactory;
    private final KnowledgeStoreFactory knowledgeStoreFactory;
    private final ModelStoreFactory modelStoreFactory;

    public static DocumentSplitter splitter() {
        return DocumentSplitters.recursive(300, 20);
    }

    public EmbeddingModel getEmbeddingModel(List<String> knowledgeIds) {
        List<String> storeIds = new ArrayList<>();
        knowledgeIds.forEach(id -> {
            if (knowledgeStoreFactory.containsKnowledge(id)) {
                AigcKnowledge data = knowledgeStoreFactory.getKnowledge(id);
                if (data.getEmbedModelId() != null) {
                    storeIds.add(data.getEmbedModelId());
                }
            }
        });
        if (storeIds.isEmpty()) {
            throw new ServiceException("知识库缺少Embedding Model配置，请先检查配置");
        }

        HashSet<String> filterIds = new HashSet<>(storeIds);
        if (filterIds.size() > 1) {
            throw new ServiceException("存在多个不同Embedding Model的知识库，请先检查配置");
        }

        return modelStoreFactory.getEmbeddingModel(storeIds.get(0));
    }

    public EmbeddingModel getEmbeddingModel(String knowledgeId) {
        if (knowledgeStoreFactory.containsKnowledge(knowledgeId)) {
            AigcKnowledge data = knowledgeStoreFactory.getKnowledge(knowledgeId);
            if (modelStoreFactory.containsEmbeddingModel(data.getEmbedModelId())) {
                return modelStoreFactory.getEmbeddingModel(data.getEmbedModelId());
            }
        }
        throw new ServiceException("没有找到匹配的Embedding向量数据库");
    }

    public EmbeddingStore<TextSegment> getEmbeddingStore(String knowledgeId) {
        if (knowledgeStoreFactory.containsKnowledge(knowledgeId)) {
            AigcKnowledge data = knowledgeStoreFactory.getKnowledge(knowledgeId);
            if (embeddingStoreFactory.containsEmbeddingStore(data.getEmbedStoreId())) {
                return embeddingStoreFactory.getEmbeddingStore(data.getEmbedStoreId());
            }
        }
        throw new ServiceException("没有找到匹配的Embedding向量数据库");
    }

    public EmbeddingStore<TextSegment> getEmbeddingStore(List<String> knowledgeIds) {
        List<String> storeIds = new ArrayList<>();
        knowledgeIds.forEach(id -> {
            if (knowledgeStoreFactory.containsKnowledge(id)) {
                AigcKnowledge data = knowledgeStoreFactory.getKnowledge(id);
                if (data.getEmbedStoreId() != null) {
                    storeIds.add(data.getEmbedStoreId());
                }
            }
        });
        if (storeIds.isEmpty()) {
            throw new ServiceException("知识库缺少Embedding Store配置，请先检查配置");
        }

        HashSet<String> filterIds = new HashSet<>(storeIds);
        if (filterIds.size() > 1) {
            throw new ServiceException("存在多个不同Embedding Store数据源的知识库，请先检查配置");
        }

        return embeddingStoreFactory.getEmbeddingStore(storeIds.get(0));
    }

}
