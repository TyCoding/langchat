package cn.tycoding.langchat.aigc.endpoint;

import cn.dev33.satoken.stp.StpUtil;
import cn.tycoding.langchat.aigc.service.ChatService;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.utils.PromptUtil;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@RequestMapping("/aigc/chat")
@RestController
@AllArgsConstructor
public class KnowledgeChatEndpoint {

    private final ChatService chatService;

    @PostMapping("/knowledge")
    public Object chat(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage()));
        req.setChatId(StpUtil.getLoginIdAsString());

        chatService.chat(req);
        return emitter.get();
    }

}
