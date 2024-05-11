package cn.tycoding.langchat.core.service;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

/**
 * @author tycoding
 * @since 2024/3/8
 */
public interface Assistant {

    @SystemMessage("""
            Respond in the language of the user's question,
            """)
    TokenStream chat(@UserMessage ChatMessage messages);
}
