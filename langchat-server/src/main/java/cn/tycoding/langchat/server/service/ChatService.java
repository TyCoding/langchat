package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.common.constant.PromptConst;
import cn.tycoding.langchat.server.utils.ChatR;
import cn.tycoding.langchat.server.utils.TextR;
import cn.tycoding.langchat.server.utils.ImageR;
import cn.tycoding.langchat.server.entity.LcOss;

/**
 * @author tycoding
 * @since 2024/1/4
 */
public interface ChatService {

    /**
     * 流式响应
     */
    void chat(ChatR req);

    void stream(TextR req, PromptConst promptConst);

    /**
     * 文本请求
     */
    String text(TextR req, PromptConst promptConst);

    /**
     * 文生图
     */
    LcOss image(ImageR req);
}
