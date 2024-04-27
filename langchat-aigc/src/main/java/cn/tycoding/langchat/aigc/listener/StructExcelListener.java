package cn.tycoding.langchat.aigc.listener;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.excel.metadata.data.ReadCellData;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tycoding
 * @since 2024/4/27
 */
@Slf4j
public class StructExcelListener extends AnalysisEventListener<Map<Integer, String>> {

    Map<Integer, String> cols = new HashMap<>();
    private static final int HEAD_ROW_NUM = 1;
    private List<CellExtra> cellExtraList = new ArrayList<>();
    private List<Map> list = new ArrayList<>();

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext analysisContext) {
        log.info("----");
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("----");
        //读取完成 填充合并过的单元格
        if (cellExtraList != null && cellExtraList.size() > 0) {
            mergeExcelData(list, cellExtraList, HEAD_ROW_NUM);
        }
    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        headMap.forEach((k,v) -> {
            cols.put(v.getColumnIndex(), v.getStringValue());
            log.info("header {}, {}", v.getColumnIndex(), v.getStringValue());
        });
    }

    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        log.info(" extra -> {}", extra);
        CellExtraTypeEnum type = extra.getType();
        switch (type) {
            case MERGE: {
                if (extra.getRowIndex() >= HEAD_ROW_NUM) {
                    cellExtraList.add(extra);
                }
                break;
            }
            default:{
            }
        }
    }

    private void mergeExcelData(List<Map> excelDataList, List<CellExtra> cellExtraList, int headRowNum) {
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

    private void setInitValueToList(Object filedValue, Integer rowIndex, Integer columnIndex, List<Map> data) {
        Map object = data.get(rowIndex);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
            if (annotation != null) {
                if (annotation.index() == columnIndex) {
                    try {
                        field.set(object, filedValue);
                        break;
                    } catch (IllegalAccessException e) {
                        log.error("设置合并单元格的值异常：{}", e.getMessage());
                    }
                }
            }
        }
    }

    private Object getInitValueFromList(Integer firstRowIndex, Integer firstColumnIndex, List<Map> data) {
        Object filedValue = null;
        Map object = data.get(firstRowIndex);
        return "menu1";
//        for (Field field : object.getClass().getDeclaredFields()) {
//            field.setAccessible(true);
//            ExcelProperty annotation = field.getAnnotation(ExcelProperty.class);
//            if (annotation != null) {
//                if (annotation.index() == firstColumnIndex) {
//                    try {
//                        filedValue = field.get(object);
//                        break;
//                    } catch (IllegalAccessException e) {
//                        log.error("设置合并单元格的初始值异常：{}", e.getMessage());
//                    }
//                }
//            }
//        }
//        return filedValue;
    }

}
