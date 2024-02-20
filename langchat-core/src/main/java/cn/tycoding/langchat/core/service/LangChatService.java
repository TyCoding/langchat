package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.common.dto.ChatData;
import cn.tycoding.langchat.core.utils.ChatReq;
import cn.tycoding.langchat.core.utils.OssR;
import cn.tycoding.langchat.core.utils.StreamEmitter;
import dev.langchain4j.model.input.Prompt;

/**
 * @author tycoding
 * @since 2023/8/29
 */
public interface LangChatService {

    void chat(StreamEmitter emitter, Prompt prompt, ChatData data);

    void stream(ChatReq req);

    String text(ChatReq req);

    OssR image(ChatReq req);
}
