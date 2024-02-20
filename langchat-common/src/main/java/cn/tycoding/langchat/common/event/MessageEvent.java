package cn.tycoding.langchat.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author tycoding
 * @since 2024/2/20
 */
public class MessageEvent extends ApplicationEvent {

    public MessageEvent(Object source) {
        super(source);
    }
}
