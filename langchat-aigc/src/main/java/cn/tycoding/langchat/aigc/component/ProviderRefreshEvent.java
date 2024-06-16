package cn.tycoding.langchat.aigc.component;

import org.springframework.context.ApplicationEvent;

/**
 * @author tycoding
 * @since 2024/6/16
 */
public class ProviderRefreshEvent extends ApplicationEvent {
    private static final long serialVersionUID = 4109980679877560773L;

    public ProviderRefreshEvent(Object source) {
        super(source);
    }
}
