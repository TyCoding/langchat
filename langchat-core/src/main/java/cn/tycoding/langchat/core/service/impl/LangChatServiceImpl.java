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

package cn.tycoding.langchat.core.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.core.provider.EmbeddingProvider;
import cn.tycoding.langchat.core.provider.ModelProvider;
import cn.tycoding.langchat.core.provider.SearchProvider;
import cn.tycoding.langchat.core.service.Agent;
import cn.tycoding.langchat.core.service.LangChatService;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.content.retriever.WebSearchContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static cn.tycoding.langchat.core.consts.EmbedConst.KNOWLEDGE;
import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Slf4j
@Service
@AllArgsConstructor
public class LangChatServiceImpl implements LangChatService {

    private final ModelProvider provider;
    private final EmbeddingProvider embeddingProvider;
    private final SearchProvider searchProvider;
    private final PgVectorEmbeddingStore embeddingStore;

    @Override
    public TokenStream chat(ChatReq req) {
        StreamingChatLanguageModel model = provider.stream(req.getModelId());
        if (StrUtil.isBlank(req.getConversationId())) {
            req.setConversationId(IdUtil.simpleUUID());
        }

        AiServices<Agent> aiServices = AiServices.builder(Agent.class)
                .streamingChatLanguageModel(model)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(5));

        EmbeddingModel embeddingModel = embeddingProvider.embed();

        if (req.getIsGoogleSearch()) {
            ContentRetriever webSearchContentRetriever;
            if (searchProvider.get() == null) {
                webSearchContentRetriever = WebSearchContentRetriever.builder().maxResults(3).build();
            } else {
                webSearchContentRetriever = WebSearchContentRetriever.builder().maxResults(3).webSearchEngine(searchProvider.get()).build();
            }

            QueryRouter queryRouter = new DefaultQueryRouter(webSearchContentRetriever);
            RetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                    .queryRouter(queryRouter)
                    .build();
            aiServices.retrievalAugmentor(retrievalAugmentor);
        }

        if (StrUtil.isNotBlank(req.getKnowledgeId())) {
            req.getKnowledgeIds().add(req.getKnowledgeId());
        }

        if (StrUtil.isNotBlank(req.getKnowledgeId())) {
            Function<Query, Filter> filter = (query) -> metadataKey(KNOWLEDGE).isIn(req.getKnowledgeIds());
            ContentRetriever contentRetriever = EmbeddingStoreContentRetriever.builder()
                    .embeddingStore(embeddingStore)
                    .embeddingModel(embeddingModel)
                    .dynamicFilter(filter)
                    .build();
            aiServices.contentRetriever(contentRetriever);
        }

        Agent agent = aiServices.build();
        return agent.stream(req.getConversationId(), req.getPrompt().text());
    }

    @Override
    public TokenStream singleChat(ChatReq req) {
        StreamingChatLanguageModel model = provider.stream(req.getModelId());
        if (StrUtil.isBlank(req.getConversationId())) {
            req.setConversationId(IdUtil.simpleUUID());
        }

        Agent agent = AiServices.builder(Agent.class)
                .streamingChatLanguageModel(model)
                .build();
        return agent.stream(req.getConversationId(), req.getPrompt().text());
    }

    @Override
    public String text(ChatReq req) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        if (StrUtil.isBlank(req.getConversationId())) {
            req.setConversationId(IdUtil.simpleUUID());
        }

        try {
            StreamingChatLanguageModel model = provider.stream(req.getModelId());
            Agent agent = AiServices.builder(Agent.class)
                    .streamingChatLanguageModel(model)
                    .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(5))
                    .build();

            StringBuilder text = new StringBuilder();
            agent.stream(req.getConversationId(), req.getPrompt().text())
                    .onNext(text::append)
                    .onComplete((t) -> {
                        future.complete(null);
                    })
                    .onError(future::completeExceptionally)
                    .start();

            future.join();
            return text.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Response<Image> image(ImageR req) {
        try {
            ImageModel model = provider.image(req.getModelId());
            return model.generate(req.getPrompt().text());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("图片生成失败");
        }
    }
}
