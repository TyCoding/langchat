package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.server.utils.QueryPage;
import cn.tycoding.langchat.server.entity.LcConversation;
import cn.tycoding.langchat.server.entity.LcMessage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tycoding
 * @since 2023/8/4
 */
public interface MessageService extends IService<LcMessage> {

    /**
     * 获取会话列表
     */
    List<LcConversation> conversations();

    /**
     * 获取会话分页列表
     */
    IPage<LcConversation> conversationPages(LcConversation data, QueryPage queryPage);

    /**
     * 新增会话
     */
    void addConversation(LcConversation conversation);

    /**
     * 修改会话
     */
    void updateConversation(LcConversation conversation);

    /**
     * 删除会话
     */
    void delConversation(Long id);

    void addMessage(LcMessage message);
}

