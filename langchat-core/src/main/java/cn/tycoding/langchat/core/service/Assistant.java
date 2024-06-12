package cn.tycoding.langchat.core.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.TokenStream;
import dev.langchain4j.service.UserMessage;

/**
 * @author tycoding
 * @since 2024/3/8
 */
public interface Assistant {

    TokenStream stream(@MemoryId String id, @UserMessage String message);
}
