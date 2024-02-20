package cn.tycoding.langchat.common.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author tycoding
 * @since 2023/10/19
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(Object source) {
        super(source);
    }
}
