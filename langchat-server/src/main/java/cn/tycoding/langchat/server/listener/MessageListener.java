package cn.tycoding.langchat.server.listener;

import cn.tycoding.langchat.common.constant.RoleEnum;
import cn.tycoding.langchat.common.event.MessageEvent;
import cn.tycoding.langchat.core.utils.ChatReq;
import cn.tycoding.langchat.server.entity.LcMessage;
import cn.tycoding.langchat.server.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2023/10/19
 */
@Component
@RequiredArgsConstructor
public class MessageListener {

    private final MessageService messageService;

    @Async
    @Order
    @EventListener(MessageEvent.class)
    public void handler(MessageEvent event) {
        ChatReq data = (ChatReq) event.getSource();
        LcMessage message = new LcMessage();
        BeanUtils.copyProperties(data, message);
        message.setRole(RoleEnum.ASSISTANT.getName());
        messageService.addMessage(message);
    }
}
