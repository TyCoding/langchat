package cn.tycoding.langchat.auth.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义定义 Log事件
 *
 * @author tycoding
 * @since 2024/1/5
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(Object source) {
        super(source);
    }
}
