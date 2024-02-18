package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.core.utils.ChatReq;
import cn.tycoding.langchat.core.utils.OssR;

/**
 * @author tycoding
 * @since 2023/8/29
 */
public interface LangChatService {

    void chat(ChatReq req);

    void stream(ChatReq req);

    String text(ChatReq req);

    OssR image(ChatReq req);
}
