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

package cn.tycoding.langchat.server.api;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.ai.biz.entity.AigcApp;
import cn.tycoding.langchat.ai.biz.entity.AigcAppApi;
import cn.tycoding.langchat.ai.core.service.LangChatService;
import cn.tycoding.langchat.common.ai.dto.ChatReq;
import cn.tycoding.langchat.common.ai.utils.StreamEmitter;
import cn.tycoding.langchat.common.core.exception.ServiceException;
import cn.tycoding.langchat.server.api.auth.CompletionReq;
import cn.tycoding.langchat.server.api.auth.CompletionRes;
import cn.tycoding.langchat.server.api.auth.OpenapiAuth;
import cn.tycoding.langchat.server.consts.AppConst;
import cn.tycoding.langchat.server.store.AppChannelStore;
import cn.tycoding.langchat.server.store.AppStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/7/26
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AppApiChatEndpoint {

    private final LangChatService langChatService;
    private final AppStore appStore;

    @OpenapiAuth(AppConst.CHANNEL_API)
    @PostMapping(value = "/chat/completions")
    public SseEmitter completions(@RequestBody CompletionReq req) {
        StreamEmitter emitter = new StreamEmitter();
        AigcAppApi appApi = AppChannelStore.getApiChannel();

        return handler(emitter, appApi.getAppId(), req.getMessages());
    }

    private SseEmitter handler(StreamEmitter emitter, String appId, List<CompletionReq.Message> messages) {
        if (messages == null || messages.isEmpty() || StrUtil.isBlank(appId)) {
            throw new RuntimeException("聊天消息为空，或者没有配置模型信息");
        }
        CompletionReq.Message message = messages.get(0);

        AigcApp app = appStore.get(appId);
        if (app == null) {
            throw new ServiceException("没有配置应用信息");
        }
        ChatReq req = new ChatReq()
                .setMessage(message.getContent())
                .setRole(message.getRole())
                .setModelId(app.getModelId())
                .setPromptText(app.getPrompt())
                .setKnowledgeIds(app.getKnowledgeIds());

        langChatService
                .singleChat(req)
                .onNext(token -> {
                    CompletionRes res = CompletionRes.process(token);
                    emitter.send(res);
                }).onComplete(c -> {
                    CompletionRes res = CompletionRes.end(c);
                    emitter.send(res);
                    emitter.complete();
                }).onError(e -> {
                    emitter.error(e.getMessage());
                }).start();

        return emitter.get();
    }
}
