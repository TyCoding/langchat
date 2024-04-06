package cn.tycoding.langchat.core.service.impl;

import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.dto.TextR;
import cn.tycoding.langchat.core.ModelProvider;
import cn.tycoding.langchat.core.service.Assistant;
import cn.tycoding.langchat.core.service.LangChatService;
import cn.tycoding.langchat.core.service.PersistentChatMemoryStore;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.TokenStream;
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
    private final PersistentChatMemoryStore chatMemoryStore;

    @Override
    public TokenStream stream(ChatReq req) {
        StreamingChatLanguageModel model = provider.stream(req.getModel());

        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(chatMemoryStore)
                .build();

        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);
        Assistant assistant = AiServices.builder(Assistant.class)
                .streamingChatLanguageModel(model)
                .chatMemory(chatMemory)
                .chatMemoryProvider(chatMemoryProvider)
                .build();
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
