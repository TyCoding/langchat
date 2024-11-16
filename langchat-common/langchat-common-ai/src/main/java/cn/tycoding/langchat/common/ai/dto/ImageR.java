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

package cn.tycoding.langchat.common.ai.dto;

import dev.langchain4j.model.input.Prompt;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2024/1/6
 */
@Data
@Accessors(chain = true)
public class ImageR {

    private String modelId;
    private String modelName;
    private String modelProvider;

    private Prompt prompt;

    /**
     * 内容
     */
    private String message;

    /**
     * 质量
     */
    private String quality;

    /**
     * 尺寸
     */
    private String size;

    /**
     * 风格
     */
    private String style;
}
