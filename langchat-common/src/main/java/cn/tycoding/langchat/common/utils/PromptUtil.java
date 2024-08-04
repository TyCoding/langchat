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

package cn.tycoding.langchat.common.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.tycoding.langchat.common.dto.PromptConst;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

/**
 * @author tycoding
 * @since 2024/3/1
 */
public class PromptUtil {

    public static Prompt build(String message) {
        return new Prompt(message);
    }

    public static Prompt build(String message, String promptText) {
        return new PromptTemplate(promptText + PromptConst.EMPTY).apply(Map.of(PromptConst.QUESTION, message));
    }

    public static Prompt build(String message, String promptText, Object param) {
        Map<String, Object> params = BeanUtil.beanToMap(param, false, true);
        params.put(PromptConst.QUESTION, message);
        return new PromptTemplate(promptText).apply(params);
    }

    public static Prompt buildDocs(String message) {
        return new PromptTemplate(PromptConst.DOCUMENT).apply(Map.of(PromptConst.QUESTION, message));
    }
}
