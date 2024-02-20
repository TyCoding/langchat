package cn.tycoding.langchat.server.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.common.constant.RoleEnum;
import cn.tycoding.langchat.server.utils.AuthUtil;
import cn.tycoding.langchat.server.utils.QueryPage;
import cn.tycoding.langchat.server.entity.LcConversation;
import cn.tycoding.langchat.server.entity.LcMessage;
import cn.tycoding.langchat.server.mapper.ConversationMapper;
import cn.tycoding.langchat.server.mapper.MessageMapper;
import cn.tycoding.langchat.server.service.MessageService;
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
 * @since 2023/8/4
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
    public void addConversation(LcConversation conversation) {
        String title = conversation.getTitle();
        conversation.setTitle(StrUtil.isBlank(title) ? "New Chat" : title)
                .setUserId(AuthUtil.getUserId()).setCreateTime(new Date());
        conversationMapper.insert(conversation);
    }

    @Override
    public void updateConversation(LcConversation conversation) {
        conversationMapper.updateById(
                new LcConversation().setId(conversation.getId()).setTitle(conversation.getTitle()));
    }

    @Override
    public void delConversation(Long id) {
        conversationMapper.deleteById(id);
    }

    @Override
    public void addMessage(LcMessage message) {
        if (StrUtil.isBlank(message.getConversationId()) && RoleEnum.USER.getName()
                .equals(message.getRole())) {
            // create new conversation
            LcConversation conversation = new LcConversation();
            addConversation(conversation);
            message.setConversationId(conversation.getId());
        }

        baseMapper.insert(message);
    }
}

