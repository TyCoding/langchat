package cn.tycoding.langchat.core.provider;

import cn.tycoding.langchat.biz.component.ProviderRefreshEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/6/16
 */
@Slf4j
@Component
@AllArgsConstructor
public class ProviderListener {

    private final ProviderInitialize providerInitialize;

    @EventListener
    public void providerEvent(ProviderRefreshEvent event) {
        log.info("refresh provider beans begin......");
        providerInitialize.init();
        log.info("refresh provider beans success......");
    }
}
