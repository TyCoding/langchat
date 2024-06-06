package cn.tycoding.langchat.core.service.impl;

import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.dto.TextR;
import cn.tycoding.langchat.core.provider.ModelProvider;
import cn.tycoding.langchat.core.service.Assistant;
import cn.tycoding.langchat.core.service.LangChatService;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
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
import dev.langchain4j.web.search.google.customsearch.GoogleCustomWebSearchEngine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Slf4j
@Service
@AllArgsConstructor
public class LangChatServiceImpl implements LangChatService {

    private final ModelProvider provider;
    private final ChatMemoryService chatMemoryService;
    private final GoogleCustomWebSearchEngine googleCustomWebSearchEngine;

    @Override
    public TokenStream stream(ChatReq req) {
        StreamingChatLanguageModel model = provider.stream(req.getModel());

        ChatMemoryProvider chatMemoryProvider = (memoryId) -> MessageWindowChatMemory.builder()
                .id(req.getConversationId() == null ? memoryId : req.getConversationId())
                .maxMessages(10)
                .chatMemoryStore(chatMemoryService)
                .build();

        Assistant assistant;
        if (req.getIsGoogleSearch()) {
            ContentRetriever webSearchContentRetriever = WebSearchContentRetriever.builder()
                    .webSearchEngine(googleCustomWebSearchEngine)
                    .maxResults(3)
                    .build();
            QueryRouter queryRouter = new DefaultQueryRouter(webSearchContentRetriever);
            RetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                    .queryRouter(queryRouter)
                    .build();
            assistant = AiServices.builder(Assistant.class)
                    .streamingChatLanguageModel(model)
                    .retrievalAugmentor(retrievalAugmentor)
                    .chatMemoryProvider(chatMemoryProvider)
                    .build();
        } else {
            assistant = AiServices.builder(Assistant.class)
                    .streamingChatLanguageModel(model)
                    .chatMemoryProvider(chatMemoryProvider)
                    .build();
        }
        return assistant.chat(req.getPrompt().toUserMessage());
    }

    @Override
    public Response<AiMessage> text(TextR req) {
        try {
            ChatLanguageModel model = provider.text(req.getModel());
            return model.generate(req.getPrompt().toUserMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Response<Image> image(ImageR req) {
        try {
            ImageModel model = provider.image(req.getModel());
            return model.generate(req.getPrompt().text());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Response<AiMessage> textImage(TextR req) {
        try {
            ChatLanguageModel model = provider.text(req.getModel());
            return model.generate(req.getPrompt().toUserMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
