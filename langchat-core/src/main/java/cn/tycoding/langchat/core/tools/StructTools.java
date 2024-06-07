package cn.tycoding.langchat.core.tools;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcExcelCol;
import cn.tycoding.langchat.aigc.entity.AigcExcelRow;
import cn.tycoding.langchat.aigc.service.AigcExcelColService;
import cn.tycoding.langchat.aigc.service.AigcExcelRowService;
import cn.tycoding.langchat.common.dto.ChatReq;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import dev.langchain4j.agent.tool.Tool;

import java.util.ArrayList;
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

    @Tool("Get the total number of columns")
    List<String> getCols() {
        List<AigcExcelCol> list = excelColService.list(Wrappers.<AigcExcelCol>lambdaQuery()
                .eq(StrUtil.isNotBlank(req.getKnowledgeId()), AigcExcelCol::getKnowledgeId, req.getKnowledgeId())
                .eq(StrUtil.isNotBlank(req.getDocsId()), AigcExcelCol::getDocsId, req.getDocsId())
                .select(AigcExcelCol::getLabel)
        );
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list.stream().map(AigcExcelCol::getLabel).toList();
    }

    @Tool("Gets data for a column")
    List<String> getColData(int col) {
        List<AigcExcelRow> list = excelRowService.list(Wrappers.<AigcExcelRow>lambdaQuery()
                .eq(StrUtil.isNotBlank(req.getKnowledgeId()), AigcExcelRow::getKnowledgeId, req.getKnowledgeId())
                .eq(StrUtil.isNotBlank(req.getDocsId()), AigcExcelRow::getDocsId, StrUtil.isNotBlank(req.getDocsId()))
                .eq(AigcExcelRow::getColIndex, col)
                .select(AigcExcelRow::getValue)
        );
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list.stream().map(AigcExcelRow::getValue).toList();
    }
}
