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

package cn.tycoding.langchat.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.tycoding.langchat.ai.biz.entity.AigcConversation;
import cn.tycoding.langchat.ai.biz.entity.AigcMessage;
import cn.tycoding.langchat.ai.biz.service.AigcMessageService;
import cn.tycoding.langchat.common.core.annotation.ApiLog;
import cn.tycoding.langchat.common.core.utils.MybatisUtil;
import cn.tycoding.langchat.common.core.utils.QueryPage;
import cn.tycoding.langchat.common.core.utils.R;
import cn.tycoding.langchat.common.core.utils.ServletUtil;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@Slf4j
@RestController
@RequestMapping("/aigc/conversation")
@AllArgsConstructor
public class AigcConversationController {

    private final AigcMessageService aigcMessageService;

    /**
     * conversation list, filter by user
     */
    @GetMapping("/list")
    public R conversations() {
        return R.ok(aigcMessageService.conversations(String.valueOf(AuthUtil.getUserId())));
    }

    /**
     * conversation page
     */
    @GetMapping("/page")
    public R list(AigcConversation data, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(aigcMessageService.conversationPages(data, queryPage)));
    }

    @PostMapping
    @ApiLog("添加会话窗口")
    @SaCheckPermission("aigc:conversation:add")
    public R addConversation(@RequestBody AigcConversation conversation) {
        conversation.setUserId(String.valueOf(AuthUtil.getUserId()));
        return R.ok(aigcMessageService.addConversation(conversation));
    }

    @PutMapping
    @ApiLog("更新会话窗口")
    @SaCheckPermission("aigc:conversation:update")
    public R updateConversation(@RequestBody AigcConversation conversation) {
        if (conversation.getId() == null) {
            return R.fail("conversation id is null");
        }
        aigcMessageService.updateConversation(conversation);
        return R.ok();
    }

    @DeleteMapping("/{conversationId}")
    @ApiLog("删除会话窗口")
    @SaCheckPermission("aigc:conversation:delete")
    public R delConversation(@PathVariable String conversationId) {
        aigcMessageService.delConversation(conversationId);
        return R.ok();
    }

    @DeleteMapping("/message/{conversationId}")
    @ApiLog("清空会话窗口数据")
    @SaCheckPermission("aigc:conversation:clear")
    public R clearMessage(@PathVariable String conversationId) {
        aigcMessageService.clearMessage(conversationId);
        return R.ok();
    }

    /**
     * get messages with conversationId
     */
    @GetMapping("/messages/{conversationId}")
    public R getMessages(@PathVariable String conversationId) {
        List<AigcMessage> list = aigcMessageService.getMessages(conversationId);
        return R.ok(list);
    }

    /**
     * add message in conversation
     */
    @PostMapping("/message")
    public R addMessage(@RequestBody AigcMessage message) {
        message.setIp(ServletUtil.getIpAddr());
        return R.ok(aigcMessageService.addMessage(message));
    }
}
