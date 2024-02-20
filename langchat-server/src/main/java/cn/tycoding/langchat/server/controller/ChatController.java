package cn.tycoding.langchat.server.controller;

import cn.tycoding.langchat.core.utils.ChatRes;
import cn.tycoding.langchat.core.utils.StreamEmitter;
import cn.tycoding.langchat.common.constant.PromptConst;
import cn.tycoding.langchat.server.utils.ChatR;
import cn.tycoding.langchat.server.utils.TextR;
import cn.tycoding.langchat.server.utils.ImageR;
import cn.tycoding.langchat.server.utils.R;
import cn.tycoding.langchat.server.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author tycoding
 * @since 2023/12/30
 */
@RequestMapping("/langchat/chat")
@RestController
@AllArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public SseEmitter chat(@RequestBody ChatR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        chatService.chat(req);
        return emitter.get();
    }

    @PostMapping("/translate")
    public SseEmitter translate(@RequestBody TextR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        chatService.stream(req, PromptConst.TRANSLATE);
        return emitter.get();
    }

    @PostMapping("/write")
    public SseEmitter write(@RequestBody TextR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        chatService.stream(req, PromptConst.WRITE);
        return emitter.get();
    }

    @PostMapping("/mindmap")
    public R mindmap(@RequestBody TextR req) {
        return R.ok(new ChatRes(chatService.text(req, PromptConst.MIND_MAP)));
    }

    @PostMapping("/chart")
    public R chart(@RequestBody TextR req) {
        return R.ok(new ChatRes(chatService.text(req, PromptConst.LINE_CHART)));
    }

    @PostMapping("/image")
    public R image(@RequestBody ImageR req) {
        return R.ok(chatService.image(req));
    }
}
