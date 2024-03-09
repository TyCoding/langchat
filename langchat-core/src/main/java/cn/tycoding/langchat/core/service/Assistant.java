package cn.tycoding.langchat.core.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.service.UserMessage;

/**
 * @author tycoding
 * @since 2024/3/8
 */
public interface Assistant {

    String chat(@UserMessage ChatMessage messages, StreamingResponseHandler<AiMessage> handler);
}
