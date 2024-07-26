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

package cn.tycoding.langchat.core.properties.chat;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "langchat.ollama")
public class OllamaProps {

    private String baseUrl;
    private String modelName;
    private Double temperature;
    private Integer topK;
    private Double topP;
    private Double repeatPenalty;
    private Integer seed;
    private Integer numPredict;
    private List<String> stop;
    private String format;
    private Duration timeout = Duration.ofSeconds(3 * 60);
}
