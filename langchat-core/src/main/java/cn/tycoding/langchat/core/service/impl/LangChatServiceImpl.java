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
import cn.tycoding.langchat.core.provider.ModelProvider;
import cn.tycoding.langchat.core.provider.SearchProvider;
import cn.tycoding.langchat.core.service.Assistant;
import cn.tycoding.langchat.core.service.LangChatService;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.WebSearchContentRetriever;
import dev.langchain4j.rag.query.router.DefaultQueryRouter;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Slf4j
@Service
@AllArgsConstructor
public class LangChatServiceImpl implements LangChatService {

    private final ModelProvider provider;
    private final SearchProvider searchProvider;

    @Override
    public TokenStream chat(ChatReq req) {
        StreamingChatLanguageModel model = provider.stream(req.getModelId());
        if (StrUtil.isBlank(req.getConversationId())) {
            req.setConversationId(IdUtil.simpleUUID());
        }

        Assistant assistant;
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
            assistant = AiServices.builder(Assistant.class)
                    .streamingChatLanguageModel(model)
                    .retrievalAugmentor(retrievalAugmentor)
                    .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(5))
                    .build();
        } else {
            assistant = AiServices.builder(Assistant.class)
                    .streamingChatLanguageModel(model)
                    .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(5))
                    .build();
        }
        return assistant.stream(req.getConversationId(), req.getPrompt().text());
    }

    @Override
    public TokenStream singleChat(ChatReq req) {
        StreamingChatLanguageModel model = provider.stream(req.getModelId());
        if (StrUtil.isBlank(req.getConversationId())) {
            req.setConversationId(IdUtil.simpleUUID());
        }

        Assistant  assistant = AiServices.builder(Assistant.class)
                .streamingChatLanguageModel(model)
                .build();
        return assistant.stream(req.getConversationId(), req.getPrompt().text());
    }

    @Override
    public String text(ChatReq req) {
        CompletableFuture<Void> future = new CompletableFuture<>();
        if (StrUtil.isBlank(req.getConversationId())) {
            req.setConversationId(IdUtil.simpleUUID());
        }

        try {
            StreamingChatLanguageModel model = provider.stream(req.getModelId());
            Assistant assistant = AiServices.builder(Assistant.class)
                    .streamingChatLanguageModel(model)
                    .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(5))
                    .build();

            StringBuilder text = new StringBuilder();
            assistant.stream(req.getConversationId(), req.getPrompt().text())
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
