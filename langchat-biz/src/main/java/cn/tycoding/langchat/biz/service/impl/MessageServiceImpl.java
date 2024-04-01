package cn.tycoding.langchat.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.entity.LcConversation;
import cn.tycoding.langchat.biz.entity.LcMessage;
import cn.tycoding.langchat.biz.mapper.ConversationMapper;
import cn.tycoding.langchat.biz.mapper.MessageMapper;
import cn.tycoding.langchat.biz.service.MessageService;
import cn.tycoding.langchat.biz.utils.AuthUtil;
import cn.tycoding.langchat.common.constant.RoleEnum;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Service
@RequiredArgsConstructor
public class MessageServiceImpl extends ServiceImpl<MessageMapper, LcMessage> implements
        MessageService {

    private final ConversationMapper conversationMapper;

    @Override
    public List<LcConversation> conversations() {
        //TODO 只获取当前用户下的会话
        return conversationMapper.selectList(
                Wrappers.<LcConversation>lambdaQuery().orderByDesc(LcConversation::getCreateTime));
    }

    @Override
    public IPage<LcConversation> conversationPages(LcConversation data, QueryPage queryPage) {
        //TODO 只获取当前用户下的会话
        Page<LcConversation> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        return conversationMapper.selectPage(page, Wrappers.<LcConversation>lambdaQuery()
                .like(!StrUtil.isBlank(data.getTitle()), LcConversation::getTitle, data.getTitle())
                .orderByDesc(LcConversation::getCreateTime));
    }

    @Override
    public LcConversation addConversation(LcConversation conversation) {
        String title = conversation.getTitle();
        if (StrUtil.isBlank(title)) {
            Long count = conversationMapper.selectCount(Wrappers.lambdaQuery());
            title = "New Chat" + (count == 0 ? "" : count);
        }
        conversation.setTitle(title)
                .setUserId(AuthUtil.getUserId()).setCreateTime(new Date());
        conversationMapper.insert(conversation);
        return conversation;
    }

    @Override
    public void updateConversation(LcConversation conversation) {
        conversationMapper.updateById(
                new LcConversation().setId(conversation.getId()).setTitle(conversation.getTitle()));
    }

    @Override
    public void delConversation(String conversationId) {
        conversationMapper.deleteById(conversationId);
        baseMapper.delete(
                Wrappers.<LcMessage>lambdaQuery().eq(LcMessage::getConversationId, conversationId));
    }

    @Override
    public LcMessage addMessage(LcMessage message) {
        if (StrUtil.isBlank(message.getConversationId()) && RoleEnum.USER.getName()
                .equals(message.getRole())) {
            // create new conversation
            LcConversation conversation = new LcConversation();
            addConversation(conversation);
            message.setConversationId(conversation.getId());
        }

        message.setCreateTime(new Date());
        baseMapper.insert(message);
        return message;
    }

    @Override
    public void clearMessage(String conversationId) {
        baseMapper.delete(
                Wrappers.<LcMessage>lambdaQuery().eq(LcMessage::getConversationId, conversationId));
    }
}

