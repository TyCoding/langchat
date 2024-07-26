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

package cn.tycoding.langchat.common.dto;

import cn.tycoding.langchat.common.utils.StreamEmitter;
import dev.langchain4j.model.input.Prompt;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2024/1/4
 */
@Data
@Accessors(chain = true)
public class TextR {

    private StreamEmitter emitter;
    private Prompt prompt;

    private String model;

    /**
     * 输入内容
     */
    private String message;

    /**
     * 角色
     */
    private String role;

    /**
     * 输出内容类型
     */
    private String type;

    /**
     * 输出内容语言
     */
    private String language;

    /**
     * 输出内容语气
     */
    private String tone;

    /**
     * 输出内容长度
     */
    private String length;
}
