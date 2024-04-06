package cn.tycoding.langchat.core.service;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

/**
 * @author tycoding
 * @since 2024/3/8
 */
public interface Assistant {

    TokenStream chat(@UserMessage ChatMessage messages);

    TokenStream chat(String query);
}
