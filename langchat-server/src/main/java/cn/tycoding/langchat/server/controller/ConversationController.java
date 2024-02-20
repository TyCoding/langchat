package cn.tycoding.langchat.server.controller;

import cn.tycoding.langchat.server.utils.MybatisUtil;
import cn.tycoding.langchat.server.utils.QueryPage;
import cn.tycoding.langchat.server.utils.R;
import cn.tycoding.langchat.server.entity.LcConversation;
import cn.tycoding.langchat.server.entity.LcMessage;
import cn.tycoding.langchat.server.service.MessageService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tycoding
 * @since 2023/8/30
 */
@Slf4j
@RestController
@RequestMapping("/langchat/conversation")
@AllArgsConstructor
public class ConversationController {

    private final MessageService messageService;

    /**
     * 会话列表
     */
    @GetMapping("/list")
    public R conversations() {
        return R.ok(messageService.conversations());
    }

    /**
     * 分页数据
     */
    @GetMapping("/page")
    public R list(LcConversation data, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(messageService.conversationPages(data, queryPage)));
    }

    /**
     * 新增会话
     */
    @PostMapping
    public R addConversation(@RequestBody LcConversation conversation) {
        messageService.addConversation(conversation);
        return R.ok();
    }

    /**
     * 修改会话
     */
    @PutMapping
    public R updateConversation(@RequestBody LcConversation conversation) {
        if (conversation.getId() == null) {
            return R.fail("参数错误");
        }
        messageService.updateConversation(conversation);
        return R.ok();
    }

    /**
     * 删除会话
     */
    @DeleteMapping("/{conversationId}")
    public R delConversation(@PathVariable String conversationId) {
        messageService.delConversation(conversationId);
        return R.ok();
    }

    @DeleteMapping("/message/{conversationId}")
    public R clearMessage(@PathVariable String conversationId) {
        messageService.clearMessage(conversationId);
        return R.ok();
    }

    /**
     * 获取指定会话下的所有聊天记录
     */
    @GetMapping("/messages/{conversationId}")
    public R messages(@PathVariable String conversationId) {
        List<LcMessage> list = messageService.list(Wrappers.<LcMessage>lambdaQuery()
                .eq(LcMessage::getConversationId, conversationId)
                .orderByAsc(LcMessage::getCreateTime)
        );

        //TODO 处理会话
        return R.ok(list);
    }

    @PostMapping("/message")
    public R addMessage(@RequestBody LcMessage message) {
        messageService.addMessage(message);
        return R.ok();
    }
}
