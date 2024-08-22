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

package cn.tycoding.langchat.server.endpoint;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.app.entity.AigcApp;
import cn.tycoding.langchat.app.service.AigcAppService;
import cn.tycoding.langchat.biz.entity.AigcMessage;
import cn.tycoding.langchat.biz.service.AigcMessageService;
import cn.tycoding.langchat.common.constant.RoleEnum;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.properties.ChatProps;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.core.service.impl.PersistentChatMemoryStore;
import cn.tycoding.langchat.server.service.ChatService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@RequestMapping("/aigc")
@RestController
@AllArgsConstructor
public class ChatEndpoint {

    private final ChatService chatService;
    private final AigcMessageService messageService;
    private final AigcAppService appService;
    private final ChatProps chatProps;

    @PostMapping("/chat/completions")
    @SaCheckPermission("chat:completions")
    public SseEmitter chat(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setUserId(String.valueOf(AuthUtil.getUserId()));
        req.setUsername(AuthUtil.getUsername());

        chatService.chat(req);
        return emitter.get();
    }

    @GetMapping("/app/info")
    public R<AigcApp> appInfo(@RequestParam String appId, String conversationId) {
        AigcApp app = appService.getById(appId);
        if (StrUtil.isBlank(conversationId)) {
            conversationId = app.getId();
        }

        if (StrUtil.isNotBlank(app.getPrompt())) {
            // initialize chat memory
            SystemMessage message = new SystemMessage(app.getPrompt());
            PersistentChatMemoryStore.init(conversationId, message);
        }

        return R.ok(app);
    }

    @GetMapping("/chat/messages/{conversationId}")
    public R messages(@PathVariable String conversationId) {
        List<AigcMessage> list = messageService.getMessages(conversationId, String.valueOf(AuthUtil.getUserId()));

        // initialize chat memory
        List<ChatMessage> chatMessages = new ArrayList<>();
        list.forEach(item -> {
            if (chatMessages.size() >= chatProps.getMemoryMaxMessage()) {
                return;
            }
            if (item.getRole().equals(RoleEnum.ASSISTANT.getName())) {
                chatMessages.add(new AiMessage(item.getMessage()));
            } else {
                chatMessages.add(new UserMessage(item.getMessage()));
            }
        });
        PersistentChatMemoryStore.init(conversationId, chatMessages);
        return R.ok(list);
    }

    @DeleteMapping("/chat/messages/clean/{conversationId}")
    @SaCheckPermission("chat:messages:clean")
    public R cleanMessage(@PathVariable String conversationId) {
        messageService.clearMessage(conversationId);

        // clean chat memory
        PersistentChatMemoryStore.clean(conversationId);
        return R.ok();
    }
}
