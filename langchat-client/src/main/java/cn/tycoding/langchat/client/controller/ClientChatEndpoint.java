/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
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
import cn.hutool.core.lang.UUID;
import cn.tycoding.langchat.biz.entity.AigcOss;
import cn.tycoding.langchat.biz.service.AigcOssService;
import cn.tycoding.langchat.biz.utils.ClientAuthUtil;
import cn.tycoding.langchat.client.dto.WriteR;
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
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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

    @ClientPerm
    @PostMapping("/chat")
    public Object chat(@RequestBody ChatReq req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setUserId(ClientAuthUtil.getUserId());
        req.setUsername(ClientAuthUtil.getUsername());
        clientChatService.chat(req);
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
    @PostMapping("/chat/write")
    public SseEmitter write(@RequestBody WriteR req) {
        StreamEmitter emitter = new StreamEmitter();
        req.setEmitter(emitter);
        req.setConversationId(UUID.randomUUID().toString());
        Dict params = Dict.create().set("profession", req.getProfession())
                .set("type", req.getType())
                .set("tone", req.getTone())
                .set("language", req.getLanguage())
                .set("length", req.getLength());
        req.setPrompt(PromptUtil.build(req.getMessage(), PromptConst.WRITE, params));
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
