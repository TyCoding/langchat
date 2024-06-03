package cn.tycoding.langchat.aigc.endpoint;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.dto.DocsTypeEnum;
import cn.tycoding.langchat.aigc.entity.AigcDocs;
import cn.tycoding.langchat.aigc.entity.AigcDocsSlice;
import cn.tycoding.langchat.aigc.entity.AigcOss;
import cn.tycoding.langchat.aigc.listener.StructExcelListener;
import cn.tycoding.langchat.aigc.service.AigcExcelColService;
import cn.tycoding.langchat.aigc.service.AigcExcelRowService;
import cn.tycoding.langchat.aigc.service.AigcKnowledgeService;
import cn.tycoding.langchat.aigc.service.AigcOssService;
import cn.tycoding.langchat.common.dto.ChatReq;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.core.service.LangDocService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.enums.CellExtraTypeEnum;
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
    private final AigcOssService aigcOssService;
    private final AigcExcelColService structColService;
    private final AigcExcelRowService structRowService;

    @PostMapping("/text")
    public R text(@RequestBody AigcDocs data) {
        if (StrUtil.isBlankIfStr(data.getContent())) {
            throw new ServiceException("文档内容不能为空");
        }
        data.setType(DocsTypeEnum.INPUT.name()).setSliceStatus(false);
        aigcKnowledgeService.addDocs(data);
        EmbeddingR embeddingR = langDocService.embeddingText(
                new ChatReq().setMessage(data.getContent())
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
    public R docs(MultipartFile file, @PathVariable Long knowledgeId) {
        AigcOss oss = aigcOssService.upload(file);
        AigcDocs data = new AigcDocs()
                .setName(oss.getFileName())
                .setSliceStatus(false)
                .setSize(file.getSize())
                .setType(DocsTypeEnum.UPLOAD.name())
                .setKnowledgeId(knowledgeId);
        aigcKnowledgeService.addDocs(data);

        List<EmbeddingR> list = langDocService.embeddingDocs(
                new ChatReq().setKnowledgeId(knowledgeId).setPath(oss.getPath()));
        list.forEach(i -> {
            aigcKnowledgeService.addDocsSlice(new AigcDocsSlice()
                    .setKnowledgeId(data.getKnowledgeId())
                    .setDocsId(data.getId())
                    .setVectorId(i.getVectorId())
                    .setName(data.getName())
                    .setContent(i.getText())
            );
        });

        aigcKnowledgeService.updateDocs(new AigcDocs().setId(data.getId()).setSliceStatus(true).setSliceNum(list.size()));
        return R.ok();
    }

    @PostMapping("/struct/excel/{knowledgeId}")
    public R structExcel(MultipartFile file, @PathVariable Long knowledgeId) throws IOException {
        byte[] bytes = file.getBytes();
        AigcOss oss = aigcOssService.upload(file);
        AigcDocs data = new AigcDocs()
                .setName(oss.getFileName())
                .setSliceStatus(false)
                .setSize(file.getSize())
                .setType(DocsTypeEnum.UPLOAD.name())
                .setKnowledgeId(knowledgeId);
        aigcKnowledgeService.addDocs(data);

        log.info("解析开始...");
        EasyExcel.read(new ByteArrayInputStream(bytes), new StructExcelListener(structColService, structRowService, knowledgeId, data.getId()))
                .extraRead(CellExtraTypeEnum.MERGE)
                .sheet().doRead();
        return R.ok();
    }
}
