package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.core.ModelProvider;
import cn.tycoding.langchat.core.dto.ChatReq;
import cn.tycoding.langchat.core.dto.ImageR;
import cn.tycoding.langchat.core.dto.TextR;
import dev.langchain4j.chain.ConversationalChain;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.image.ImageModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.service.AiServices;
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
public class LangChatService {

    private final ModelProvider provider;

    public void stream(ChatReq req, StreamingResponseHandler<AiMessage> handler) {
        StreamingChatLanguageModel model = provider.stream(req.getModel());

        Assistant assistant = AiServices.builder(Assistant.class)
                .streamingChatLanguageModel(model)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();
        assistant.chat(req.getPrompt().toUserMessage(), handler);
//        model.generate(messages, handler);
    }

    public Response<AiMessage> text(TextR req) {
        try {
            ChatLanguageModel model = provider.text(req.getModel());
            return model.generate(req.getPrompt().toUserMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Response<Image> image(ImageR req) {
        try {
            ImageModel model = provider.image(req.getModel());
            return model.generate(req.getPrompt().text());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
