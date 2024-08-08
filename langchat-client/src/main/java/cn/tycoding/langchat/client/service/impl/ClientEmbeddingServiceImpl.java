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

package cn.tycoding.langchat.client.service.impl;

import cn.tycoding.langchat.client.service.ClientEmbeddingService;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.core.service.LangEmbeddingService;
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

    private final LangEmbeddingService langEmbeddingService;
    private final PgVectorEmbeddingStore embeddingStore;

    @Async
    @Override
    public void embedDocs(ChatReq data) {
        langEmbeddingService.embeddingDocs(data);
    }

    @Override
    public void deleteVector(String knowledgeId) {
        Filter filter = metadataKey(KNOWLEDGE).isEqualTo(knowledgeId);
        embeddingStore.removeAll(filter);
    }
}
