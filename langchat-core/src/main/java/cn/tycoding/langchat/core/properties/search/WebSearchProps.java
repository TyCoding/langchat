package cn.tycoding.langchat.core.properties.search;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/6/6
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "langchat.web-search")
public class WebSearchProps {

    private GoogleProps google = new GoogleProps();
}
