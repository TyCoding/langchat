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

package cn.tycoding.langchat.biz.component;

import lombok.Getter;
import lombok.Setter;

/**
 * @author tycoding
 * @since 2024/6/16
 */
@Getter
public enum ProviderEnum {

    OPENAI("openai"),
    AZURE_OPENAI("azure-openai"),
    GOOGLE("google"),
    OLLAMA("ollama"),
    BAIDU("baidu"),
    ALIBABA("alibaba"),
    ZHIPU("zhipu"),
    TEXT_IMAGE("text-image"),
    EMBEDDING("embedding"),
    WEB_SEARCH("web-search"),

    TEXT_IMAGE_DALLE2("dall-e-2"),
    TEXT_IMAGE_DALLE3("dall-e-3"),
    ;

    @Setter
    private String model;

    @Setter
    private String streamClass;

    ProviderEnum(String model) {
        this.model = model;
    }
}
