/*
 * Project: LangChat
 * Author: TyCoding
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

package cn.tycoding.langchat.client.controller;

import cn.tycoding.langchat.biz.entity.AigcConversation;
import cn.tycoding.langchat.biz.entity.AigcMessage;
import cn.tycoding.langchat.biz.service.AigcMessageService;
import cn.tycoding.langchat.biz.utils.ClientAuthUtil;
import cn.tycoding.langchat.common.annotation.ClientPerm;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.common.utils.ServletUtil;
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
@RequestMapping("/client/conversation")
@AllArgsConstructor
public class ClientConversationController {

    private final AigcMessageService aigcMessageService;

    /**
     * conversation list, filter by user
     */
    @GetMapping("/list")
    public R conversations() {
        return R.ok(aigcMessageService.conversations(ClientAuthUtil.getUserId()));
    }

    /**
     * conversation page
     */
    @GetMapping("/page")
    public R list(AigcConversation data, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(aigcMessageService.conversationPages(data, queryPage)));
    }

    @ClientPerm
    @PostMapping
    public R addConversation(@RequestBody AigcConversation conversation) {
        conversation.setUserId(ClientAuthUtil.getUserId());
        return R.ok(aigcMessageService.addConversation(conversation));
    }

    @ClientPerm
    @PutMapping
    public R updateConversation(@RequestBody AigcConversation conversation) {
        if (conversation.getId() == null) {
            return R.fail("conversation id is null");
        }
        aigcMessageService.updateConversation(conversation);
        return R.ok();
    }

    @ClientPerm
    @DeleteMapping("/{conversationId}")
    public R delConversation(@PathVariable String conversationId) {
        aigcMessageService.delConversation(conversationId);
        return R.ok();
    }

    @ClientPerm
    @DeleteMapping("/message/{conversationId}")
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

    @ClientPerm
    @PostMapping("/message")
    public R addMessage(@RequestBody AigcMessage message) {
        message.setIp(ServletUtil.getIpAddr());
        return R.ok(aigcMessageService.addMessage(message));
    }
}
