package cn.tycoding.langchat.core.provider;

import cn.tycoding.langchat.common.component.SpringContextHolder;
import dev.langchain4j.web.search.google.customsearch.GoogleCustomWebSearchEngine;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/7/16
 */
@Slf4j
@Component
@AllArgsConstructor
public class SearchProvider {

    private final SpringContextHolder springContextHolder;

    public GoogleCustomWebSearchEngine get() {
        try {
            return springContextHolder.getBean(GoogleCustomWebSearchEngine.class);
        } catch (Exception e) {
            log.error("GoogleWebSearch is not configured");
            return null;
        }
    }
}
