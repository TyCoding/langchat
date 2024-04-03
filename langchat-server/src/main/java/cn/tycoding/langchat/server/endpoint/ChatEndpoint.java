package cn.tycoding.langchat.server.endpoint;

import cn.tycoding.langchat.biz.entity.SysOss;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ChatRes;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.dto.TextR;
import cn.tycoding.langchat.common.dto.PromptConst;
import cn.tycoding.langchat.common.utils.PromptUtil;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.core.enums.ModelConst;
import cn.tycoding.langchat.server.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@RequestMapping("/langchat/chat")
@RestController
@AllArgsConstructor
public class ChatEndpoint {

    private final ChatService chatService;

    @PostMapping
    public Object chat(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage()));

        if (req.getModel().endsWith(ModelConst.IMAGE_SUFFIX)) {
            SysOss oss = chatService.image(new ImageR().setPrompt(req.getPrompt()).setModel(req.getModel()));
            emitter.send("Image:" + oss);
            emitter.complete();
        } else {
            chatService.chat(req);
        }
        return emitter.get();
    }

    @PostMapping("/translate")
    public SseEmitter translate(@RequestBody TextR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.TRANSLATE));
        chatService.stream(req);
        return emitter.get();
    }

    @PostMapping("/write")
    public SseEmitter write(@RequestBody TextR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.CHART_LINE));
        chatService.stream(req);
        return emitter.get();
    }

    @PostMapping("/mindmap")
    public R mindmap(@RequestBody TextR req) {
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.MINDMAP));
        return R.ok(new ChatRes(chatService.text(req)));
    }

    @PostMapping("/chart")
    public R chart(@RequestBody TextR req) {
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.CHART_LINE));
        return R.ok(new ChatRes(chatService.text(req)));
    }

    @PostMapping("/image")
    public R image(@RequestBody ImageR req) {
        return R.ok(chatService.image(req));
    }
}
