package cn.tycoding.langchat.biz.service;

import cn.tycoding.langchat.biz.entity.SysConversation;
import cn.tycoding.langchat.biz.entity.SysMessage;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/4
 */
public interface MessageService extends IService<SysMessage> {

    /**
     * 获取会话列表
     */
    List<SysConversation> conversations();

    /**
     * 获取会话分页列表
     */
    IPage<SysConversation> conversationPages(SysConversation data, QueryPage queryPage);

    /**
     * 新增会话
     */
    SysConversation addConversation(SysConversation conversation);

    /**
     * 修改会话
     */
    void updateConversation(SysConversation conversation);

    /**
     * 删除会话
     */
    void delConversation(String conversationId);

    SysMessage addMessage(SysMessage message);

    void clearMessage(String conversationId);
}

