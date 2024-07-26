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

package cn.tycoding.langchat.core.properties.search;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @author tycoding
 * @since 2024/6/6
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "langchat.web-search.google")
public class GoogleProps {

    private String apiKey;
    private String csi;
    private Boolean siteRestrict;
    private Boolean includeImages;
    private Duration timeout = Duration.ofSeconds(600);
    private Integer maxRetries;
    private Boolean logRequests;
    private Boolean logResponses;
}
