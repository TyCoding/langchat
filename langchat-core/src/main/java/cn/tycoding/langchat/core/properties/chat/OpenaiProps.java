/*
 * Project: LangChat
 * Author: TyCoding
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

import cn.tycoding.langchat.core.properties.image.OpenaiImageProps;
import dev.langchain4j.model.Tokenizer;
import java.net.Proxy;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "langchat.openai")
public class OpenaiProps {

    private OpenaiImageProps image = new OpenaiImageProps();

    private String baseUrl;
    private String apiKey;
    private String organizationId;
    private String modelName;
    private Double temperature = 1.0;
    private Double topP = 1.0;
    private List<String> stop;
    private Integer maxTokens = 4096;
    private Double presencePenalty = 0.0;
    private Double frequencyPenalty = 0.0;
    private Map<String, Integer> logitBias;
    private String responseFormat;
    private Integer seed;
    private String user;
    private Duration timeout = Duration.ofSeconds(3 * 60);
    private Proxy proxy;
    private Boolean logRequests;
    private Boolean logResponses;
    private Tokenizer tokenizer;
}
