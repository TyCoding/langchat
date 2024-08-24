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
import cn.tycoding.langchat.common.properties.ChatProps;
import cn.tycoding.langchat.core.provider.EmbeddingProvider;
import cn.tycoding.langchat.core.provider.ModelProvider;
import cn.tycoding.langchat.core.service.Agent;
import cn.tycoding.langchat.core.service.LangChatService;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.store.embedding.filter.Filter;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final PgVectorEmbeddingStore embeddingStore;
    private final ChatProps chatProps;

    private AiServices<Agent> build(StreamingChatLanguageModel streamModel, ChatLanguageModel model, ChatReq req) {
        AiServices<Agent> aiServices = AiServices.builder(Agent.class)
                .systemMessageProvider(memoryId -> req.getPromptText())
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.builder()
                        .id(req.getConversationId())
                        .chatMemoryStore(new PersistentChatMemoryStore())
                        .maxMessages(chatProps.getMemoryMaxMessage())
                        .build());
        if (streamModel != null) {
            aiServices.streamingChatLanguageModel(streamModel);
        }
        if (model != null) {
            aiServices.chatLanguageModel(model);
        }
        return aiServices;
    }

    @Override
    public TokenStream chat(ChatReq req) {
        StreamingChatLanguageModel model = provider.stream(req.getModelId());
        if (StrUtil.isBlank(req.getConversationId())) {
            req.setConversationId(IdUtil.simpleUUID());
        }

        AiServices<Agent> aiServices = build(model, null, req);
        EmbeddingModel embeddingModel = embeddingProvider.embed();

        if (StrUtil.isNotBlank(req.getKnowledgeId())) {
            req.getKnowledgeIds().add(req.getKnowledgeId());
        }

        if (req.getKnowledgeIds() != null && !req.getKnowledgeIds().isEmpty()) {
            Function<Query, Filter> filter = (query) -> metadataKey(KNOWLEDGE).isIn(req.getKnowledgeIds());
            ContentRetriever contentRetriever = EmbeddingStoreContentRetrieverCustom.builder()
                    .memoryId(req.getConversationId())
                    .embeddingStore(embeddingStore)
                    .embeddingModel(embeddingModel)
                    .dynamicFilter(filter)
                    .build();
            aiServices.contentRetriever(contentRetriever);
            aiServices.retrievalAugmentor(DefaultRetrievalAugmentor
                    .builder()
                    .contentRetriever(contentRetriever)
                    .queryRouter(new DefaultQueryRouter())
                    .executor(req.getExecutor())
                    .build());
        }
        Agent agent = aiServices.build();
        return agent.stream(req.getConversationId(), req.getMessage());
    }

    @Override
    public TokenStream singleChat(ChatReq req) {
        StreamingChatLanguageModel model = provider.stream(req.getModelId());
        if (StrUtil.isBlank(req.getConversationId())) {
            req.setConversationId(IdUtil.simpleUUID());
        }

        Agent agent = build(model, null, req).build();
        return agent.stream(req.getConversationId(), req.getMessage());
    }

    @Override
    public String text(ChatReq req) {
        if (StrUtil.isBlank(req.getConversationId())) {
            req.setConversationId(IdUtil.simpleUUID());
        }

        try {
            ChatLanguageModel model = provider.text(req.getModelId());
            Agent agent = build(null, model, req).build();
            String text = agent.text(req.getConversationId(), req.getMessage());
            return text;
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
