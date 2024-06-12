package cn.tycoding.langchat.aigc.endpoint;

import cn.tycoding.langchat.aigc.entity.AigcOss;
import cn.tycoding.langchat.aigc.service.AigcOssService;
import cn.tycoding.langchat.aigc.service.ChatService;
import cn.tycoding.langchat.aigc.service.EmbeddingService;
import cn.tycoding.langchat.aigc.utils.AigcAuthUtil;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ChatRes;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.dto.PromptConst;
import cn.tycoding.langchat.common.utils.PromptUtil;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.core.consts.ModelConst;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/aigc/chat")
@RestController
@AllArgsConstructor
public class AigcChatEndpoint {

    private final ChatService chatService;
    private final AigcOssService aigcOssService;
    private final EmbeddingService embeddingDocs;

    @PostMapping
    public Object chat(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setUserId(AigcAuthUtil.getUserId());
        req.setUsername(AigcAuthUtil.getUsername());
        req.setPrompt(PromptUtil.build(req.getMessage()));

        if (req.getModel().endsWith(ModelConst.IMAGE_SUFFIX)) {
            AigcOss oss = chatService.image(
                    new ImageR().setPrompt(req.getPrompt()).setModel(req.getModel()));
            emitter.send("Image:" + oss);
            emitter.complete();
        } else {
            chatService.chat(req);
        }
        return emitter.get();
    }

    @PostMapping("/docs/{id}")
    public Object docs(@RequestBody ChatReq req, @PathVariable String id) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setUserId(AigcAuthUtil.getUserId());
        req.setUsername(AigcAuthUtil.getUsername());
        req.setPrompt(PromptUtil.buildDocs(req.getMessage()));
        req.setKnowledgeId(id);

        chatService.docsChat(req);
        return emitter.get();
    }

    @PostMapping("/docs/upload")
    public R docs(MultipartFile file) {
        AigcOss oss = aigcOssService.upload(file);
        embeddingDocs.embedDocs(
                new ChatReq()
                        .setDocsName(oss.getTargetName())
                        .setKnowledgeId(oss.getId())
                        .setPath(oss.getPath()));
        return R.ok(oss);
    }

    @DeleteMapping("/docs/{id}")
    public R docs(@PathVariable String id) {
        aigcOssService.removeById(id);
        // del vector store
        embeddingDocs.deleteVector(id);
        return R.ok();
    }

    @PostMapping("/translate")
    public SseEmitter translate(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.TRANSLATE));
        chatService.singleChat(req);
        return emitter.get();
    }

    @PostMapping("/write")
    public SseEmitter write(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.CHART_LINE));
        chatService.singleChat(req);
        return emitter.get();
    }

    @PostMapping("/mindmap")
    public R mindmap(@RequestBody ChatReq req) {
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.MINDMAP));
        return R.ok(new ChatRes(chatService.text(req)));
    }

    @PostMapping("/image")
    public R image(@RequestBody ImageR req) {
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.IMAGE));
        return R.ok(chatService.image(req));
    }

}
