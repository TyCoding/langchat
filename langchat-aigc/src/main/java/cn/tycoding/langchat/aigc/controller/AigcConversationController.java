package cn.tycoding.langchat.aigc.controller;

import cn.tycoding.langchat.aigc.entity.AigcConversation;
import cn.tycoding.langchat.aigc.entity.AigcMessage;
import cn.tycoding.langchat.aigc.service.AigcMessageService;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
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
     * 会话列表
     */
    @GetMapping("/list")
    public R conversations() {
        return R.ok(aigcMessageService.conversations());
    }

    /**
     * 分页数据
     */
    @GetMapping("/page")
    public R list(AigcConversation data, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(aigcMessageService.conversationPages(data, queryPage)));
    }

    /**
     * 新增会话
     */
    @PostMapping
    public R addConversation(@RequestBody AigcConversation conversation) {
        return R.ok(aigcMessageService.addConversation(conversation));
    }

    /**
     * 修改会话
     */
    @PutMapping
    public R updateConversation(@RequestBody AigcConversation conversation) {
        if (conversation.getId() == null) {
            return R.fail("参数错误");
        }
        aigcMessageService.updateConversation(conversation);
        return R.ok();
    }

    /**
     * 删除会话
     */
    @DeleteMapping("/{conversationId}")
    public R delConversation(@PathVariable String conversationId) {
        aigcMessageService.delConversation(conversationId);
        return R.ok();
    }

    @DeleteMapping("/message/{conversationId}")
    public R clearMessage(@PathVariable String conversationId) {
        aigcMessageService.clearMessage(conversationId);
        return R.ok();
    }

    /**
     * 获取指定会话下的聊天记录
     */
    @GetMapping("/messages/{conversationId}")
    public R messages(@PathVariable String conversationId) {
        List<AigcMessage> list = aigcMessageService.getMessages(conversationId);

        //TODO 处理会话
        return R.ok(list);
    }

    @PostMapping("/message")
    public R addMessage(@RequestBody AigcMessage message) {
        return R.ok(aigcMessageService.addMessage(message));
    }
}
