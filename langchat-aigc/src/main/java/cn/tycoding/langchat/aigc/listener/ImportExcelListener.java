package cn.tycoding.langchat.aigc.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellExtra;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Excel模板的读取监听类
 * @param <T>
 */
@Slf4j
public class ImportExcelListener<T> extends AnalysisEventListener<T> {

    /**
     * 解析的数据
     */
    private List<T> list = new ArrayList<>();

    /**
     * 正文起始行
     */
    private Integer headRowNumber;
    /**
     * 合并单元格
     */
    private List<CellExtra> extraMergeInfoList = new ArrayList<>();

    public ImportExcelListener(Integer headRowNumber) {
        this.headRowNumber = headRowNumber;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context context
     */
    @Override
    public void invoke(T data, AnalysisContext context) {
        log.info("解析到一条数据: {}", JSON.toJSONString(data));
        list.add(data);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！");
    }

    /**
     * 返回解析出来的List
     */
    public List<T> getData() {
        return list;
    }

    /**
     * 读取额外信息：合并单元格
     */
    @Override
    public void extra(CellExtra extra, AnalysisContext context) {
        log.info("读取到了一条额外信息:{}", JSON.toJSONString(extra));
        switch (extra.getType()) {
            case MERGE: {
                log.info(
                        "额外信息是合并单元格,而且覆盖了一个区间,在firstRowIndex:{},firstColumnIndex;{},lastRowIndex:{},lastColumnIndex:{}",
                        extra.getFirstRowIndex(), extra.getFirstColumnIndex(), extra.getLastRowIndex(),
                        extra.getLastColumnIndex());
                if (extra.getRowIndex() >= headRowNumber) {
                    extraMergeInfoList.add(extra);
                }
                break;
            }
            default:
        }
    }

    /**
     * 返回解析出来的合并单元格List
     */
    public List<CellExtra> getExtraMergeInfoList() {
        return extraMergeInfoList;
    }
}
