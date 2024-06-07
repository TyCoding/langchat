package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.properties.search.GoogleProps;
import cn.tycoding.langchat.core.properties.search.TavilyProps;
import cn.tycoding.langchat.core.properties.search.WebSearchProps;
import dev.langchain4j.web.search.google.customsearch.GoogleCustomWebSearchEngine;
import dev.langchain4j.web.search.tavily.TavilyWebSearchEngine;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static cn.tycoding.langchat.core.consts.PropConst.GOOGLE_CON;

/**
 * @author tycoding
 * @since 2024/6/6
 */
@Configuration
@AllArgsConstructor
public class WebSearchComponent {

    private final WebSearchProps props;

    @Bean
    @ConditionalOnProperty(GOOGLE_CON)
    public GoogleCustomWebSearchEngine googleCustomWebSearchEngine() {
        GoogleProps google = props.getGoogle();
        return GoogleCustomWebSearchEngine
                .builder()
                .apiKey(google.getApiKey())
                .csi(google.getCsi())
                .siteRestrict(google.getSiteRestrict())
                .includeImages(google.getIncludeImages())
                .timeout(google.getTimeout())
                .maxRetries(google.getMaxRetries())
                .logRequests(google.getLogRequests())
                .logResponses(google.getLogResponses())
                .build();
    }

    @Bean
    @ConditionalOnProperty(GOOGLE_CON)
    public TavilyWebSearchEngine tavilyWebSearchEngine() {
        TavilyProps tavily = props.getTavily();
        return TavilyWebSearchEngine
                .builder()
                .baseUrl(tavily.getBaseUrl())
                .apiKey(tavily.getApiKey())
                .timeout(tavily.getTimeout())
                .searchDepth(tavily.getSearchDepth())
                .includeAnswer(tavily.getIncludeAnswer())
                .includeRawContent(tavily.getIncludeRawContent())
                .includeDomains(tavily.getIncludeDomains())
                .excludeDomains(tavily.getExcludeDomains())
                .build();
    }

}
