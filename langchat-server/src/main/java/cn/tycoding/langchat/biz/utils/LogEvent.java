package cn.tycoding.langchat.biz.utils;

import org.springframework.context.ApplicationEvent;
/**
 * @author tycoding
 * @since 2024/1/19
 */
public class LogEvent extends ApplicationEvent {

    public LogEvent(Object source) {
        super(source);
    }
}
