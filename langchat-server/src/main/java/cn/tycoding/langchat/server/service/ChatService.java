package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.biz.entity.SysOss;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.dto.TextR;

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
    void stream(TextR req);

    /**
     * 文本请求
     */
    String text(TextR req);

    /**
     * 文生图
     */
    SysOss image(ImageR req);

    /**
     * 文档聊天
     */
    void docsChat(DocR req);

    /**
     * 文档Embedding
     */
    void docsEmbed(SysOss req);
}
