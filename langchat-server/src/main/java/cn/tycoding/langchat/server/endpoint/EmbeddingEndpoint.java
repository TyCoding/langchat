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
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.dto.DocsTypeEnum;
import cn.tycoding.langchat.biz.entity.*;
import cn.tycoding.langchat.biz.listener.StructExcelListener;
import cn.tycoding.langchat.biz.mapper.AigcDocsMapper;
import cn.tycoding.langchat.biz.service.*;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.core.service.LangDocService;
import cn.tycoding.langchat.server.service.EmbeddingService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/25
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/aigc/embedding")
public class EmbeddingEndpoint {

    private final LangDocService langDocService;
    private final AigcKnowledgeService aigcKnowledgeService;
    private final AigcDocsMapper aigcDocsMapper;
    private final AigcOssService aigcOssService;
    private final AigcExcelColService excelColService;
    private final AigcExcelRowService excelRowService;
    private final AigcExcelDataService excelDataService;
    private final EmbeddingService embeddingService;

    @PostMapping("/text")
    @SaCheckPermission("aigc:embedding:text")
    public R text(@RequestBody AigcDocs data) {
        if (StrUtil.isBlankIfStr(data.getContent())) {
            throw new ServiceException("文档内容不能为空");
        }
        data.setType(DocsTypeEnum.INPUT.name()).setSliceStatus(false);
        if (StrUtil.isBlank(data.getId())) {
            aigcKnowledgeService.addDocs(data);
        }
        EmbeddingR embeddingR = langDocService.embeddingText(
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
                .setSize(file.getSize())
                .setType(DocsTypeEnum.UPLOAD.name())
                .setKnowledgeId(knowledgeId);
        aigcKnowledgeService.addDocs(data);

        // embedding docs
        embeddingService.embedDocsSlice(data, oss.getUrl());
        return R.ok();
    }

    @PostMapping("/excel/{knowledgeId}")
    @SaCheckPermission("aigc:embedding:excel")
    public R structExcel(MultipartFile file, @PathVariable String knowledgeId) throws IOException {
        byte[] bytes = file.getBytes();
        AigcOss oss = aigcOssService.upload(file, String.valueOf(AuthUtil.getUserId()));
        AigcDocs data = new AigcDocs()
                .setName(oss.getOriginalFilename())
                .setSliceStatus(true)
                .setSize(file.getSize())
                .setType(DocsTypeEnum.UPLOAD.name())
                .setKnowledgeId(knowledgeId);
        aigcKnowledgeService.addDocs(data);

        EasyExcel.read(new ByteArrayInputStream(bytes), new StructExcelListener(excelDataService, excelColService, excelRowService, knowledgeId, data.getId()))
                .extraRead(CellExtraTypeEnum.MERGE)
                .sheet()
                .doRead();
        return R.ok();
    }

    @GetMapping("/excel/rows/{docsId}")
    public R getExcelRows(@PathVariable String docsId) {
        List<List<String>> rows = excelDataService.list(Wrappers.<AigcExcelData>lambdaQuery()
                .eq(AigcExcelData::getDocsId, docsId).orderByAsc(AigcExcelData::getRowIndex)
        ).stream().map(AigcExcelData::getData).toList();

        List<String> cols = excelColService.list(Wrappers.<AigcExcelCol>lambdaQuery()
                        .eq(AigcExcelCol::getDocsId, docsId)
                        .orderByAsc(AigcExcelCol::getColIndex))
                .stream().map(AigcExcelCol::getLabel).toList();
        return R.ok(Dict.create().set("cols", cols).set("rows", rows));
    }

    @GetMapping("/re-embed/{docsId}")
    public R reEmbed(@PathVariable String docsId) {
        AigcDocs docs = aigcDocsMapper.selectById(docsId);
        if (docs == null) {
            throw new ServiceException("没有查询到文档数据");
        }
        if ("INPUT".equals(docs.getType())) {
            text(docs);
        }
        return R.ok();
    }

    @PostMapping("/search")
    public R search(@RequestBody AigcDocs data) {
        return R.ok(embeddingService.search(data));
    }
}
