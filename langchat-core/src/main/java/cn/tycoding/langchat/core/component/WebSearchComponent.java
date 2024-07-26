/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.core.component;

import cn.tycoding.langchat.core.properties.search.GoogleProps;
import cn.tycoding.langchat.core.properties.search.WebSearchProps;
import dev.langchain4j.web.search.google.customsearch.GoogleCustomWebSearchEngine;
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
    @ConditionalOnProperty(value = GOOGLE_CON)
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
}
