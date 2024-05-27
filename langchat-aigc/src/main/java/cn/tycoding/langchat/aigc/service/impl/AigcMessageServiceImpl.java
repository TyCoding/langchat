package cn.tycoding.langchat.aigc.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcConversation;
import cn.tycoding.langchat.aigc.entity.AigcMessage;
import cn.tycoding.langchat.aigc.entity.AigcUser;
import cn.tycoding.langchat.aigc.mapper.AigcConversationMapper;
import cn.tycoding.langchat.aigc.mapper.AigcMessageMapper;
import cn.tycoding.langchat.aigc.mapper.AigcUserMapper;
import cn.tycoding.langchat.aigc.service.AigcMessageService;
import cn.tycoding.langchat.common.constant.RoleEnum;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Service
@RequiredArgsConstructor
public class AigcMessageServiceImpl extends ServiceImpl<AigcMessageMapper, AigcMessage> implements
        AigcMessageService {
    private final AigcConversationMapper aigcConversationMapper;
    private final AigcUserMapper aigcUserMapper;

    @Override
    public List<AigcConversation> conversations() {
        //TODO 只获取当前用户下的会话
        return aigcConversationMapper.selectList(
                Wrappers.<AigcConversation>lambdaQuery()
                        .orderByDesc(AigcConversation::getCreateTime));
    }

    @Override
    public IPage<AigcConversation> conversationPages(AigcConversation data, QueryPage queryPage) {
        Page<AigcConversation> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        Page<AigcConversation> iPage = aigcConversationMapper.selectPage(page, Wrappers.<AigcConversation>lambdaQuery()
                .like(!StrUtil.isBlank(data.getTitle()), AigcConversation::getTitle, data.getTitle())
                .orderByDesc(AigcConversation::getCreateTime));

        if (!iPage.getRecords().isEmpty()) {
            Map<Long, List<AigcUser>> map = aigcUserMapper.selectList(Wrappers.lambdaQuery()).stream().collect(Collectors.groupingBy(AigcUser::getId));
            Set<Long> ids = iPage.getRecords().stream().map(AigcConversation::getId).collect(Collectors.toSet());
            List<AigcMessage> messages = baseMapper.selectList(Wrappers.<AigcMessage>lambdaQuery()
                    .in(AigcMessage::getConversationId, ids)
                    .orderByDesc(AigcMessage::getCreateTime));

            iPage.getRecords().forEach(i -> {
                List<AigcUser> list = map.get(i.getUserId());
                if (list != null && !list.isEmpty()) {
                    i.setUsername(list.get(0).getUsername());
                }

                List<AigcMessage> messageList = messages.stream().filter(m -> m.getConversationId() != null && m.getConversationId().equals(i.getId())).toList();
                if (!messageList.isEmpty()) {
                    i.setChatTotal(messageList.size());
                    i.setEndTime(messageList.get(0).getCreateTime());
                    i.setTokenUsed(messageList.stream().filter(m -> m.getTokens() != null).mapToInt(AigcMessage::getTokens).sum());
                }
            });
        }
        return iPage;
    }

    @Override
    public AigcConversation addConversation(AigcConversation conversation) {
        String title = conversation.getTitle();
        if (StrUtil.isBlank(title)) {
            Long count = aigcConversationMapper.selectCount(Wrappers.lambdaQuery());
            title = "New Chat" + (count == 0 ? "" : count);
        }
//        conversation.setTitle(title).setUserId(AuthUtil.getUserId()).setCreateTime(new Date());
        aigcConversationMapper.insert(conversation);
        return conversation;
    }

    @Override
    public void updateConversation(AigcConversation conversation) {
        aigcConversationMapper.updateById(
                new AigcConversation().setId(conversation.getId())
                        .setTitle(conversation.getTitle()));
    }

    @Override
    public void delConversation(String conversationId) {
        aigcConversationMapper.deleteById(conversationId);
        baseMapper.delete(
                Wrappers.<AigcMessage>lambdaQuery()
                        .eq(AigcMessage::getConversationId, conversationId));
    }

    @Override
    public AigcMessage addMessage(AigcMessage message) {
        if (message.getConversationId() != null && RoleEnum.USER.getName()
                .equals(message.getRole())) {
            // create new conversation
            AigcConversation conversation = new AigcConversation();
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
                Wrappers.<AigcMessage>lambdaQuery()
                        .eq(AigcMessage::getConversationId, conversationId));
    }

    @Override
    public List<AigcMessage> getMessages(String conversationId) {
        // 避免页面渲染压力大，只截取最新的20条数据
        return baseMapper.selectPage(new Page<>(0, 20), Wrappers.<AigcMessage>lambdaQuery()
                .eq(AigcMessage::getConversationId, conversationId)
                .orderByDesc(AigcMessage::getCreateTime)
        ).getRecords();
    }

    @Override
    public List<AigcMessage> getMessages(String conversationId, String userId) {
        // 避免页面渲染压力大，只截取最新的100条数据
        return baseMapper.selectPage(new Page<>(0, 100), Wrappers.<AigcMessage>lambdaQuery()
                .eq(AigcMessage::getConversationId, conversationId)
                .eq(AigcMessage::getUserId, userId)
                .orderByAsc(AigcMessage::getCreateTime)
        ).getRecords();
    }
}

