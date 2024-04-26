package cn.tycoding.langchat.aigc.endpoint;

import cn.tycoding.langchat.aigc.service.AigcOssService;
import cn.tycoding.langchat.aigc.service.ChatService;
import cn.tycoding.langchat.common.component.AsyncFuture;
import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.common.dto.PromptConst;
import cn.tycoding.langchat.common.utils.PromptUtil;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@RequestMapping("/aigc/docs")
@RestController
@AllArgsConstructor
public class DocsEndpoint {

    private final ChatService chatService;
    private final AigcOssService aigcOssService;
    private final AsyncFuture asyncFuture;

    @PostMapping("/chat")
    public SseEmitter chat(@RequestBody DocR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.DOCUMENT));
        chatService.docsChat(req);
        return emitter.get();
    }



    @GetMapping("/task")
    public R task() {
        int count = asyncFuture.getCount("111");
        return R.ok(count);
    }
}
