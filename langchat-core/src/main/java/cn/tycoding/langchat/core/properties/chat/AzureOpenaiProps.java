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

import com.azure.core.http.ProxyOptions;
import dev.langchain4j.model.Tokenizer;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Data
@ConfigurationProperties(prefix = "langchat.azure-openai")
public class AzureOpenaiProps {

    private String endpoint;
    private String serviceVersion;
    private String apiKey;
    private String deploymentName;
    private Tokenizer tokenizer;
    private Integer maxTokens;
    private Double temperature;
    private Double topP;
    private String user;
    private Integer n;
    private List<String> stop;
    private Double presencePenalty;
    private Double frequencyPenalty;
    private Duration timeout = Duration.ofSeconds(3 * 60);
    private Long seed;
    private Integer maxRetries;
    private ProxyOptions proxyOptions;
    private boolean logRequestsAndResponses;
}
