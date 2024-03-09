package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.biz.entity.LcOss;
import cn.tycoding.langchat.core.dto.ChatReq;
import cn.tycoding.langchat.core.dto.ImageR;
import cn.tycoding.langchat.core.dto.TextR;

/**
 * @author tycoding
 * @since 2024/1/4
 */
public interface ChatService {

    /**
     * 流式响应
     */
    void chat(ChatReq req);

    void stream(TextR req);

    /**
     * 文本请求
     */
    String text(TextR req);

    /**
     * 文生图
     */
    LcOss image(ImageR req);
}
