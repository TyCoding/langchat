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
import cn.tycoding.langchat.biz.entity.AigcMessage;
import cn.tycoding.langchat.biz.service.AigcMessageService;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.server.service.ChatService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/chat/completions")
    @SaCheckPermission("chat:completions")
    public Object chat(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setUserId(String.valueOf(AuthUtil.getUserId()));
        req.setUsername(AuthUtil.getUsername());

        chatService.chat(req);
        return emitter.get();
    }

    @GetMapping("/chat/messages/{conversationId}")
    public R messages(@PathVariable String conversationId) {
        List<AigcMessage> list = messageService.getMessages(conversationId, String.valueOf(AuthUtil.getUserId()));
        return R.ok(list);
    }

    @DeleteMapping("/chat/messages/clean/{conversationId}")
    @SaCheckPermission("chat:messages:clean")
    public R cleanMessage(@PathVariable String conversationId) {
        messageService.clearMessage(conversationId);
        return R.ok();
    }
}
