package cn.tycoding.langchat.aigc.endpoint;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcDocs;
import cn.tycoding.langchat.aigc.entity.AigcDocsSlice;
import cn.tycoding.langchat.aigc.entity.AigcOss;
import cn.tycoding.langchat.aigc.enums.DocsTypeEnum;
import cn.tycoding.langchat.aigc.listener.StructExcelListener;
import cn.tycoding.langchat.aigc.service.AigcKnowledgeService;
import cn.tycoding.langchat.aigc.service.AigcOssService;
import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.core.service.LangDocService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import java.io.IOException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tycoding
 * @since 2024/4/25
 */
@RestController
@AllArgsConstructor
@RequestMapping("/aigc/embedding")
public class EmbeddingEndpoint {

    private final LangDocService langDocService;
    private final AigcKnowledgeService aigcKnowledgeService;
    private final AigcOssService aigcOssService;

    @Async
    @PostMapping("/text")
    public R text(@RequestBody AigcDocs data) {
        if (StrUtil.isBlankIfStr(data.getContent())) {
            throw new ServiceException("文档内容不能为空");
        }
        data.setType(DocsTypeEnum.INPUT.name()).setSliceStatus(false);
        aigcKnowledgeService.addDocs(data);
        EmbeddingR embeddingR = langDocService.embeddingText(
                new DocR().setMessage(data.getContent())
                        .setId(data.getId())
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
    public R docs(MultipartFile file, @PathVariable String knowledgeId) {
        AigcOss oss = aigcOssService.upload(file);
        AigcDocs data = new AigcDocs()
                .setName(oss.getFileName())
                .setSliceStatus(false)
                .setSize(oss.getSize())
                .setType(DocsTypeEnum.UPLOAD.name())
                .setKnowledgeId(knowledgeId);
        aigcKnowledgeService.addDocs(data);

        List<EmbeddingR> list = langDocService.embeddingDocs(
                new DocR().setKnowledgeId(knowledgeId).setPath(oss.getPath()));
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
    public void structExcel(MultipartFile file, @PathVariable String knowledgeId)
            throws IOException {
//        AigcOss oss = aigcOssService.upload(file);
//        AigcDocs data = new AigcDocs()
//                .setName(oss.getFileName())
//                .setSliceStatus(false)
//                .setSize(oss.getSize())
//                .setType(DocsTypeEnum.UPLOAD.name())
//                .setKnowledgeId(knowledgeId);
//        aigcKnowledgeService.addDocs(data);

        EasyExcel.read(file.getInputStream(), new StructExcelListener())
                .extraRead(CellExtraTypeEnum.MERGE)
                .sheet().doRead();
    }
}
