/*
 * Project: LangChat
 * Author: TyCoding
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.client.controller;

import cn.hutool.core.lang.Dict;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

/**
 * @author tycoding
 * @since 2024/1/30
 */
@Slf4j
@RequestMapping("/client")
@RestController
@AllArgsConstructor
public class ClientChatEndpoint {

    private final ClientChatService clientChatService;
    private final AigcOssService aigcOssService;
    private final ClientEmbeddingService clientEmbeddingService;

    @PostMapping("/test")
    public Object test(@RequestBody Object obj) {
        log.info("x: {}", obj);
        return Dict.create().set("message", "你好呀").set("threadId", "111");
    }

    @PostMapping("/test2")
    public Object test2(@RequestBody Object obj) throws InterruptedException, IOException {
        log.info("Received: {}", obj);
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    emitter.send("Data: " + i + "\n", MediaType.TEXT_PLAIN);
                    Thread.sleep(1000);
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }

    @ClientPerm
    @PostMapping("/chat")
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
                        .setDocsName(oss.getOriginalFilename())
                        .setKnowledgeId(oss.getId())
                        .setUrl(oss.getUrl()));
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
    @PostMapping("/chat/translate")
    public SseEmitter translate(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.TRANSLATE));
        clientChatService.singleChat(req);
        return emitter.get();
    }

    @ClientPerm
    @PostMapping("/chat/write")
    public SseEmitter write(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.CHART_LINE));
        clientChatService.singleChat(req);
        return emitter.get();
    }

    @ClientPerm
    @PostMapping("/chat/mindmap")
    public R mindmap(@RequestBody ChatReq req) {
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.MINDMAP));
        return R.ok(new ChatRes(clientChatService.text(req)));
    }

    @ClientPerm
    @PostMapping("/chat/image")
    public R image(@RequestBody ImageR req) {
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.IMAGE));
        return R.ok(clientChatService.image(req));
    }

}
