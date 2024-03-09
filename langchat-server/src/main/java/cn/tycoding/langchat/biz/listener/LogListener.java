package cn.tycoding.langchat.biz.listener;

import cn.tycoding.langchat.biz.entity.LcLog;
import cn.tycoding.langchat.biz.service.LogService;
import cn.tycoding.langchat.biz.utils.LogEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Component
@RequiredArgsConstructor
public class LogListener {

    private final LogService logService;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void handler(LogEvent event) {
        LcLog data = (LcLog) event.getSource();
        logService.add(data);
    }
}
