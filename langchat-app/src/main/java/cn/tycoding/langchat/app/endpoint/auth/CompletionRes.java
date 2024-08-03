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

import dev.langchain4j.model.output.Response;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/7/30
 */
@Data
@Builder
public class CompletionRes {

    private final String id;
    private final Integer created;
    private final String model;
    private final List<ChatCompletionChoice> choices;
    private final Usage usage;

    public static CompletionRes process(String token) {
        return CompletionRes.builder()
                .choices(List.of(ChatCompletionChoice
                        .builder()
                        .delta(Delta.builder().content(token).build())
                        .build()))
                .build();
    }

    public static CompletionRes end(Response res) {
        return CompletionRes.builder()
                .usage(Usage.builder()
                        .completionTokens(res.tokenUsage().outputTokenCount())
                        .promptTokens(res.tokenUsage().inputTokenCount())
                        .totalTokens(res.tokenUsage().totalTokenCount())
                        .build())
                .choices(List.of(ChatCompletionChoice
                        .builder()
                        .finishReason(res.finishReason() == null ? "finish" : res.finishReason().toString())
                        .build()))
                .build();
    }

    @Data
    @Builder
    static class Usage {
        private final Integer promptTokens;
        private final Integer completionTokens;
        private final Integer totalTokens;
    }

    @Data
    @Builder
    static class ChatCompletionChoice {
        private final Delta delta;
        private final String finishReason;
    }

    @Data
    @Builder
    static class Delta {
        private final String content;
    }
}
