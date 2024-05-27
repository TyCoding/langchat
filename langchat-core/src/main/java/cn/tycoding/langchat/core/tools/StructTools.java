package cn.tycoding.langchat.core.tools;

import cn.tycoding.langchat.common.dto.ChatReq;
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

    private final ChatReq req;
    private final AigcStructColService structColService;
    private final AigcStructRowService structRowService;

    public StructTools(ChatReq req, AigcStructColService structColService, AigcStructRowService structRowService) {
        this.req = req;
        this.structColService = structColService;
        this.structRowService = structRowService;
    }

    @Tool("Gets column name data in Data")
    List<String> getCols() {
        List<AigcStructCol> list = structColService.list(Wrappers.<AigcStructCol>lambdaQuery()
                .eq(req.getKnowledgeId() != null, AigcStructCol::getKnowledgeId, req.getKnowledgeId())
                .eq(req.getDocsId() != null, AigcStructCol::getDocsId, req.getDocsId())
                .select(AigcStructCol::getLabel)
        );
        return list.stream().map(AigcStructCol::getLabel).toList();
    }

    @Tool("Gets all the data for a column")
    List<String> getColData(int col) {
        List<AigcStructRow> list = structRowService.list(Wrappers.<AigcStructRow>lambdaQuery()
                .eq(req.getKnowledgeId() != null, AigcStructRow::getKnowledgeId, req.getKnowledgeId())
                .eq(req.getDocsId() != null, AigcStructRow::getDocsId, req.getDocsId())
                .eq(AigcStructRow::getColIndex, col)
                .select(AigcStructRow::getValue)
        );
        return list.stream().map(AigcStructRow::getValue).toList();
    }
}
