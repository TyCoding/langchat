package cn.tycoding.langchat.aigc.service;

import cn.tycoding.langchat.aigc.entity.AigcOss;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ImageR;

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
     * 流式请求
     */
    void singleChat(ChatReq req);

    /**
     * 文本请求
     */
    String text(ChatReq req);

    /**
     * 文生图
     */
    AigcOss image(ImageR req);

    /**
     * 文档聊天
     */
    void docsChat(ChatReq req);

}
