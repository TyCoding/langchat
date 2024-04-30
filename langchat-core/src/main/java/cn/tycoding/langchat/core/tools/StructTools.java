package cn.tycoding.langchat.core.tools;

import cn.tycoding.langchat.common.dto.DocR;
import cn.tycoding.langchat.core.entity.AigcStructCol;
import cn.tycoding.langchat.core.entity.AigcStructRow;
import cn.tycoding.langchat.core.service.AigcStructColService;
import cn.tycoding.langchat.core.service.AigcStructRowService;
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

    private final DocR req;
    private final AigcStructColService structColService;
    private final AigcStructRowService structRowService;

    public StructTools(DocR req, AigcStructColService structColService, AigcStructRowService structRowService) {
        this.req = req;
        this.structColService = structColService;
        this.structRowService = structRowService;
    }

    @Tool("Gets column name data in Data")
    List<String> getCols() {
        List<AigcStructCol> list = structColService.list(Wrappers.<AigcStructCol>lambdaQuery()
                .eq(AigcStructCol::getKnowledgeId, req.getKnowledgeId())
                .eq(AigcStructCol::getDocsId, req.getDocsId())
        );
        return list.stream().map(AigcStructCol::getLabel).toList();
    }

    @Tool("Gets all the data for a column")
    List<String> getColData(int col) {
        List<AigcStructRow> list = structRowService.list(Wrappers.<AigcStructRow>lambdaQuery()
                .eq(AigcStructRow::getKnowledgeId, req.getKnowledgeId())
                .eq(AigcStructRow::getDocsId, req.getDocsId())
                .eq(AigcStructRow::getColIndex, col)
        );
        return list.stream().map(AigcStructRow::getValue).toList();
    }
}
