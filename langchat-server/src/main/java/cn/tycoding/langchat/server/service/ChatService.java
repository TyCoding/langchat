package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.server.common.constant.PromptConst;
import cn.tycoding.langchat.server.common.utils.ChatR;
import cn.tycoding.langchat.server.common.utils.ImageR;
import cn.tycoding.langchat.server.entity.LcOss;

/**
 * @author tycoding
 * @since 2024/1/4
 */
public interface ChatService {

    /**
     * 流式响应
     */
    void stream(ChatR req, PromptConst promptConst);

    /**
     * 文本请求
     */
    String text(ChatR req, PromptConst promptConst);

    /**
     * 文生图
     */
    LcOss image(ImageR req);
}
