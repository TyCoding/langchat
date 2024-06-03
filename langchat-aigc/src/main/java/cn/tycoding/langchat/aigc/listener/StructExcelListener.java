package cn.tycoding.langchat.aigc.listener;

import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcExcelCol;
import cn.tycoding.langchat.aigc.entity.AigcExcelRow;
import cn.tycoding.langchat.aigc.service.AigcExcelColService;
import cn.tycoding.langchat.aigc.service.AigcExcelRowService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.ReadCellData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/4/27
 */
@Slf4j
public class StructExcelListener extends AnalysisEventListener<Map<Integer, String>> {

    private static final int HEAD_ROW_NUM = 1;
    private final List<AigcExcelCol> cols = new ArrayList<>();
    private final List<CellExtra> cellExtraList = new ArrayList<>();
    private final List<Map<Integer, String>> list = new ArrayList<>();

    private final AigcExcelColService structColService;
    private final AigcExcelRowService structRowService;
    private final Long knowledgeId;
    private final Long docsId;

    public StructExcelListener(AigcExcelColService structColService, AigcExcelRowService structRowService, Long knowledgeId, Long docsId) {
        this.structColService = structColService;
        this.structRowService = structRowService;
        this.knowledgeId = knowledgeId;
        this.docsId = docsId;
    }

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext analysisContext) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!cellExtraList.isEmpty()) {
            mergeExcelData(list, cellExtraList, HEAD_ROW_NUM);
        }

        structColService.saveBatch(cols);
        List<AigcExcelRow> rows = new ArrayList<>();
        list.forEach(i -> {
            i.forEach((k, v) -> {
                if (StrUtil.isNotBlank(v)) {
                    rows.add(new AigcExcelRow()
                            .setValue(v)
                            .setColIndex(k)
                            .setDocsId(docsId)
                            .setKnowledgeId(knowledgeId));
                }
            });
        });
        structRowService.saveBatch(rows);
    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        headMap.forEach((k, v) -> {
            cols.add(new AigcExcelCol()
                    .setColIndex(v.getColumnIndex())
                    .setLabel(v.getStringValue())
                    .setKnowledgeId(knowledgeId)
                    .setDocsId(docsId)
            );
        });
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        CellExtraTypeEnum type = extra.getType();
        if (type == CellExtraTypeEnum.MERGE) {
            if (extra.getRowIndex() >= HEAD_ROW_NUM) {
                cellExtraList.add(extra);
            }
        }
    }

    private void mergeExcelData(List<Map<Integer, String>> excelDataList, List<CellExtra> cellExtraList, int headRowNum) {
        cellExtraList.forEach(cellExtra -> {
            int firstRowIndex = cellExtra.getFirstRowIndex() - headRowNum;
            int lastRowIndex = cellExtra.getLastRowIndex() - headRowNum;
            int firstColumnIndex = cellExtra.getFirstColumnIndex();
            int lastColumnIndex = cellExtra.getLastColumnIndex();
            //获取初始值 合并单元格左上角的值
            Object initValue = getInitValueFromList(firstRowIndex, firstColumnIndex, excelDataList);
            //设置值 把合并单元格左上角的值 设置到合并区域的每一个单元格
            for (int i = firstRowIndex; i <= lastRowIndex; i++) {
                for (int j = firstColumnIndex; j <= lastColumnIndex; j++) {
                    setInitValueToList(initValue, i, j, excelDataList);
                }
            }
        });
    }

    private void setInitValueToList(Object filedValue, Integer rowIndex, Integer columnIndex, List<Map<Integer, String>> data) {
        Map<Integer, String> object = data.get(rowIndex);
        object.put(columnIndex, String.valueOf(filedValue));
    }

    private Object getInitValueFromList(Integer firstRowIndex, Integer firstColumnIndex, List<Map<Integer, String>> data) {
        Map<Integer, String> object = data.get(firstRowIndex);
        return object.get(firstColumnIndex);
    }

}
