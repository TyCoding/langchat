package cn.tycoding.langchat.auth.event;

import cn.tycoding.langchat.upms.entity.SysLog;
import cn.tycoding.langchat.upms.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 监听自定义Log 事件
 *
 * @author tycoding
 * @since 2024/1/5
 */
@Component
@RequiredArgsConstructor
public class LogListener {

    private final SysLogService sysLogService;

    @Async
    @Order
    @EventListener(LogEvent.class)
    public void handler(LogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        sysLogService.add(sysLog);
    }
}
