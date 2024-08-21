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

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@Data
@Accessors(chain = true)
public class ChatReq {

    private String appId;
    private String modelId;
    private String modelName;
    private String modelProvider;

    private String message;

    private String conversationId;

    private String userId;

    private String username;

    private String chatId;

    private String promptText;

    private String docsName;

    private String knowledgeId;
    private List<String> knowledgeIds = new ArrayList<>();

    private String docsId;

    private String url;

    private String role;

    private Prompt prompt;

    private StreamEmitter emitter;
}
