package cn.tycoding.langchat.server.endpoint;

import cn.tycoding.langchat.biz.entity.SysOss;
import cn.tycoding.langchat.biz.service.OssService;
import cn.tycoding.langchat.common.component.AsyncFuture;
import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.common.dto.PromptConst;
import cn.tycoding.langchat.common.utils.PromptUtil;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.server.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@RequestMapping("/langchat/docs")
@RestController
@AllArgsConstructor
public class DocsEndpoint {

    private final ChatService chatService;
    private final OssService ossService;
    private final AsyncFuture asyncFuture;

    @PostMapping("/chat")
    public SseEmitter chat(@RequestBody DocR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.CHART_LINE));
        chatService.docsChat(req);
        return emitter.get();
    }

    @PostMapping("/upload")
    public R upload(MultipartFile file) {
        SysOss oss = ossService.upload(file);
        asyncFuture.async(() -> {
            chatService.docsEmbed(oss);
        },"1","1");
        return R.ok(oss);
    }
}
