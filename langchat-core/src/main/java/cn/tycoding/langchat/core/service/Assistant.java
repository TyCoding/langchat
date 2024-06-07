package cn.tycoding.langchat.core.service;

import dev.langchain4j.service.TokenStream;

/**
 * @author tycoding
 * @since 2024/3/8
 */
public interface Assistant {

    TokenStream stream(String message);

    String chat(String message);
}
