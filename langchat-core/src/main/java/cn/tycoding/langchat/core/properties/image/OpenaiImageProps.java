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

package cn.tycoding.langchat.core.properties.image;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.Proxy;
import java.nio.file.Path;
import java.time.Duration;

/**
 * @author tycoding
 * @since 2024/3/8
 */
@Data
@ConfigurationProperties(prefix = "langchat.openai.image")
public class OpenaiImageProps {

    private String baseUrl;
    private String apiKey;
    private String organizationId;
    private String modelName;
    private String size;
    private String quality;
    private String style;
    private String user;
    private String responseFormat;
    private Duration timeout = Duration.ofSeconds(600);
    private Integer maxRetries;
    private Proxy proxy;
    private Boolean logRequests;
    private Boolean logResponses;
    private Boolean withPersisting;
    private Path persistTo;
}
