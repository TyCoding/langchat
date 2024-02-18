package cn.tycoding.langchat.core.service;

import cn.tycoding.langchat.core.utils.ChatReq;

/**
 * @author tycoding
 * @since 2024/1/5
 */
public interface LangDocService {

    /**
     * 流式请求
     */
    void stream(ChatReq req);

    /**
     * 向量解析
     */
    void embedding(String path, Long key);

    void delDoc(Long key);
}
