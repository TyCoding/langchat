package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.common.dto.ChatReq;

/**
 * @author tycoding
 * @since 2024/1/4
 */
public interface ChatService {

    /**
     * 聊天接口
     */
    void chat(ChatReq req);

    /**
     * 文档聊天
     */
    void docsChat(ChatReq req);

}
