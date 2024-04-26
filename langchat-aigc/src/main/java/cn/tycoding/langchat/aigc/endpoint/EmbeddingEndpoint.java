package cn.tycoding.langchat.aigc.endpoint;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcDocs;
import cn.tycoding.langchat.aigc.entity.AigcDocsSlice;
import cn.tycoding.langchat.aigc.service.AigcKnowledgeService;
import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.common.dto.EmbeddingR;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.core.service.LangDocService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/text")
    public void text(@RequestBody AigcDocs data) {
        if (StrUtil.isBlankIfStr(data.getContent())) {
            throw new ServiceException("文档内容不能为空");
        }
        aigcKnowledgeService.addDocs(data);
        EmbeddingR embeddingR = langDocService.embeddingText(new DocR().setMessage(data.getContent())
                .setId(data.getId())
                .setKnowledgeId(data.getKnowledgeId()));
        aigcKnowledgeService.addDocsSlice(new AigcDocsSlice()
                .setKnowledgeId(data.getKnowledgeId())
                .setDocsId(data.getId())
                .setVectorId(embeddingR.getVectorId())
                .setName(data.getName())
                .setContent(embeddingR.getText())
        );
    }
}
