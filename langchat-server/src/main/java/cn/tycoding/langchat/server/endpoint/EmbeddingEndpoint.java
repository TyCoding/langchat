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

package cn.tycoding.langchat.server.endpoint;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.entity.AigcDocs;
import cn.tycoding.langchat.biz.entity.AigcDocsSlice;
import cn.tycoding.langchat.biz.entity.AigcOss;
import cn.tycoding.langchat.biz.mapper.AigcDocsMapper;
import cn.tycoding.langchat.biz.service.AigcKnowledgeService;
import cn.tycoding.langchat.biz.service.AigcOssService;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.core.consts.EmbedConst;
import cn.tycoding.langchat.core.service.LangEmbeddingService;
import cn.tycoding.langchat.server.service.EmbeddingService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tycoding
 * @since 2024/4/25
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/aigc/embedding")
public class EmbeddingEndpoint {

    private final LangEmbeddingService langEmbeddingService;
    private final AigcKnowledgeService aigcKnowledgeService;
    private final AigcDocsMapper aigcDocsMapper;
    private final AigcOssService aigcOssService;
    private final EmbeddingService embeddingService;

    @PostMapping("/text")
    @SaCheckPermission("aigc:embedding:text")
    public R text(@RequestBody AigcDocs data) {
        if (StrUtil.isBlankIfStr(data.getContent())) {
            throw new ServiceException("文档内容不能为空");
        }
        data.setType(EmbedConst.ORIGIN_TYPE_INPUT).setSliceStatus(false);
        if (StrUtil.isBlank(data.getId())) {
            aigcKnowledgeService.addDocs(data);
        }

        EmbeddingR embeddingR = langEmbeddingService.embeddingText(
                new ChatReq().setMessage(data.getContent())
                        .setDocsName(data.getType())
                        .setDocsId(data.getId())
                        .setKnowledgeId(data.getKnowledgeId()));

        aigcKnowledgeService.addDocsSlice(new AigcDocsSlice()
                .setKnowledgeId(data.getKnowledgeId())
                .setDocsId(data.getId())
                .setVectorId(embeddingR.getVectorId())
                .setName(data.getName())
                .setContent(embeddingR.getText())
        );

        aigcKnowledgeService.updateDocs(new AigcDocs().setId(data.getId()).setSliceStatus(true).setSliceNum(1));
        return R.ok();
    }

    @PostMapping("/docs/{knowledgeId}")
    @SaCheckPermission("aigc:embedding:docs")
    public R docs(MultipartFile file, @PathVariable String knowledgeId) {
        AigcOss oss = aigcOssService.upload(file, String.valueOf(AuthUtil.getUserId()));
        AigcDocs data = new AigcDocs()
                .setName(oss.getOriginalFilename())
                .setSliceStatus(false)
                .setUrl(oss.getUrl())
                .setSize(file.getSize())
                .setType(EmbedConst.ORIGIN_TYPE_UPLOAD)
                .setKnowledgeId(knowledgeId);
        aigcKnowledgeService.addDocs(data);

        // embedding docs
        embeddingService.embedDocsSlice(data, oss.getUrl());
        return R.ok();
    }

    @GetMapping("/re-embed/{docsId}")
    public R reEmbed(@PathVariable String docsId) {
        AigcDocs docs = aigcDocsMapper.selectById(docsId);
        if (docs == null) {
            throw new ServiceException("没有查询到文档数据");
        }
        if (EmbedConst.ORIGIN_TYPE_INPUT.equals(docs.getType())) {
            text(docs);
        }
        if (EmbedConst.ORIGIN_TYPE_UPLOAD.equals(docs.getType())) {
            embeddingService.embedDocsSlice(docs, docs.getUrl());
        }
        return R.ok();
    }

    @PostMapping("/search")
    public R search(@RequestBody AigcDocs data) {
        return R.ok(embeddingService.search(data));
    }
}
