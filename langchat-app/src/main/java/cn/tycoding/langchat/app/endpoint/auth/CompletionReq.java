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

package cn.tycoding.langchat.app.endpoint.auth;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/7/30
 */
@Data
@Builder
public class CompletionReq {

    private final String model;
    private final List<Message> messages;
    private final Double temperature;
    private final Double topP;
    private final Integer n;
    private final Boolean stream;
    private final List<String> stop;
    private final Integer maxTokens;
    private final Double presencePenalty;
    private final Double frequencyPenalty;
    private final String user;
    private final Integer seed;

    @Data
    @Builder
    public static class Message {
        String role;
        String content;
    }
}
