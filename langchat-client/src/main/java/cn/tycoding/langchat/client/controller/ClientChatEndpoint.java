package cn.tycoding.langchat.client.controller;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.entity.AigcOss;
import cn.tycoding.langchat.biz.service.AigcOssService;
import cn.tycoding.langchat.biz.utils.ClientAuthUtil;
import cn.tycoding.langchat.client.service.ClientChatService;
import cn.tycoding.langchat.client.service.ClientEmbeddingService;
import cn.tycoding.langchat.common.annotation.ClientPerm;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.ChatRes;
import cn.tycoding.langchat.common.dto.ImageR;
import cn.tycoding.langchat.common.dto.PromptConst;
import cn.tycoding.langchat.common.utils.PromptUtil;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.common.utils.StreamEmitter;
import cn.tycoding.langchat.core.consts.ModelConst;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@RequestMapping("/client/chat")
@RestController
@AllArgsConstructor
public class ClientChatEndpoint {

    private final ClientChatService clientChatService;
    private final AigcOssService aigcOssService;
    private final ClientEmbeddingService clientEmbeddingService;

    @PostMapping
    @ClientPerm
    public Object chat(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setUserId(ClientAuthUtil.getUserId());
        req.setUsername(ClientAuthUtil.getUsername());

        if (StrUtil.isBlank(req.getPromptId())) {
            req.setPrompt(PromptUtil.build(req.getMessage()));
        } else {
            req.setPrompt(PromptUtil.build(req.getMessage(), req.getPromptText()));
        }

        if (req.getModelId().endsWith(ModelConst.IMAGE_SUFFIX)) {
            AigcOss oss = clientChatService.image(
                    new ImageR().setPrompt(req.getPrompt()).setModel(req.getModelId()));
            emitter.send("Image:" + oss);
            emitter.complete();
        } else {
            clientChatService.chat(req);
        }
        return emitter.get();
    }

    @ClientPerm
    @PostMapping("/docs/{id}")
    public Object docsChat(@RequestBody ChatReq req, @PathVariable String id) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setUserId(ClientAuthUtil.getUserId());
        req.setUsername(ClientAuthUtil.getUsername());
        req.setPrompt(PromptUtil.buildDocs(req.getMessage()));
        req.setKnowledgeId(id);

        clientChatService.docsChat(req);
        return emitter.get();
    }

    @ClientPerm
    @PostMapping("/docs/upload")
    public R docsUpload(MultipartFile file) {
        AigcOss oss = aigcOssService.upload(file, ClientAuthUtil.getUserId());
        clientEmbeddingService.embedDocs(
                new ChatReq()
                        .setDocsName(oss.getTargetName())
                        .setKnowledgeId(oss.getId())
                        .setPath(oss.getPath()));
        return R.ok(oss);
    }

    @ClientPerm
    @DeleteMapping("/docs/{id}")
    public R docsDel(@PathVariable String id) {
        aigcOssService.removeById(id);
        // del vector store
        clientEmbeddingService.deleteVector(id);
        return R.ok();
    }

    @ClientPerm
    @PostMapping("/translate")
    public SseEmitter translate(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.TRANSLATE));
        clientChatService.singleChat(req);
        return emitter.get();
    }

    @ClientPerm
    @PostMapping("/write")
    public SseEmitter write(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.CHART_LINE));
        clientChatService.singleChat(req);
        return emitter.get();
    }

    @ClientPerm
    @PostMapping("/mindmap")
    public R mindmap(@RequestBody ChatReq req) {
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.MINDMAP));
        return R.ok(new ChatRes(clientChatService.text(req)));
    }

    @ClientPerm
    @PostMapping("/image")
    public R image(@RequestBody ImageR req) {
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.IMAGE));
        return R.ok(clientChatService.image(req));
    }

}
