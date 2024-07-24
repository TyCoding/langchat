package cn.tycoding.langchat.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.tycoding.langchat.biz.entity.AigcConversation;
import cn.tycoding.langchat.biz.entity.AigcMessage;
import cn.tycoding.langchat.biz.service.AigcMessageService;
import cn.tycoding.langchat.biz.utils.ClientAuthUtil;
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.common.utils.ServletUtil;
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
        conversation.setUserId(ClientAuthUtil.getUserId());
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
