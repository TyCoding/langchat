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

package cn.tycoding.langchat.app.endpoint;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.app.consts.AppConst;
import cn.tycoding.langchat.app.endpoint.auth.CompletionReq;
import cn.tycoding.langchat.app.endpoint.auth.CompletionRes;
import cn.tycoding.langchat.app.endpoint.auth.OpenapiAuth;
import cn.tycoding.langchat.app.entity.AigcApp;
import cn.tycoding.langchat.app.entity.AigcAppApi;
import cn.tycoding.langchat.app.entity.AigcAppWeb;
import cn.tycoding.langchat.app.store.AppChannelStore;
import cn.tycoding.langchat.app.store.AppStore;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.core.service.LangChatService;
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

        return handler(emitter, appApi.getAppId(), appApi.getModelId(), req.getMessages());
    }

    private SseEmitter handler(StreamEmitter emitter, String appId, String modelId, List<CompletionReq.Message> messages) {
        if (messages == null || messages.isEmpty() || StrUtil.isBlank(modelId)) {
            throw new RuntimeException("聊天消息为空，或者没有配置模型信息");
        }
        CompletionReq.Message message = messages.get(0);
        ChatReq req = new ChatReq()
                .setMessage(message.getContent())
                .setRole(message.getRole())
                .setModelId(modelId);

        if (StrUtil.isNotBlank(appId)) {
            if (StrUtil.isNotBlank(appId)) {
                AigcApp app = appStore.get(appId);
                if (app == null) {
                    throw new ServiceException("没有配置应用信息");
                }

                req.setModelId(app.getModelId());
                req.setPromptText(req.getPromptText());
                req.setKnowledgeIds(app.getKnowledgeIds());
            }
        }

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

    @OpenapiAuth(AppConst.CHANNEL_WEB)
    @PostMapping(value = "/chat/completions/channel/web")
    public SseEmitter webChat(@RequestBody CompletionReq req) {
        StreamEmitter emitter = new StreamEmitter();
        AigcAppWeb appWeb = AppChannelStore.getWebChannel();

        return handler(emitter, appWeb.getAppId(), appWeb.getModelId(), req.getMessages());
    }
}
