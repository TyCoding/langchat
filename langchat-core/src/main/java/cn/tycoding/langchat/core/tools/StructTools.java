package cn.tycoding.langchat.core.tools;

import cn.tycoding.langchat.aigc.entity.AigcExcelCol;
import cn.tycoding.langchat.aigc.entity.AigcExcelRow;
import cn.tycoding.langchat.aigc.service.AigcExcelColService;
import cn.tycoding.langchat.aigc.service.AigcExcelRowService;
import cn.tycoding.langchat.common.dto.ChatReq;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import dev.langchain4j.agent.tool.Tool;

import java.util.List;

/**
 * 对于结构化数据的function call
 *
 * @author tycoding
 * @since 2024/4/28
 */
public class StructTools {

    private final ChatReq req;
    private final AigcExcelColService excelColService;
    private final AigcExcelRowService excelRowService;

    public StructTools(ChatReq req, AigcExcelColService excelColService, AigcExcelRowService excelRowService) {
        this.req = req;
        this.excelColService = excelColService;
        this.excelRowService = excelRowService;
    }

    @Tool("Gets column name data in Data")
    List<String> getCols() {
        List<AigcExcelCol> list = excelColService.list(Wrappers.<AigcExcelCol>lambdaQuery()
                .eq(req.getKnowledgeId() != null, AigcExcelCol::getKnowledgeId, req.getKnowledgeId())
                .eq(req.getDocsId() != null, AigcExcelCol::getDocsId, req.getDocsId())
                .select(AigcExcelCol::getLabel)
        );
        return list.stream().map(AigcExcelCol::getLabel).toList();
    }

    @Tool("Gets all the data for a column")
    List<String> getColData(int col) {
        List<AigcExcelRow> list = excelRowService.list(Wrappers.<AigcExcelRow>lambdaQuery()
                .eq(req.getKnowledgeId() != null, AigcExcelRow::getKnowledgeId, req.getKnowledgeId())
                .eq(req.getDocsId() != null, AigcExcelRow::getDocsId, req.getDocsId())
                .eq(AigcExcelRow::getColIndex, col)
                .select(AigcExcelRow::getValue)
        );
        return list.stream().map(AigcExcelRow::getValue).toList();
    }
}
