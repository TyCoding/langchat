package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.biz.entity.SysOss;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.dto.TextR;

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
    SysOss image(ImageR req);
}
