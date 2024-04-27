package cn.tycoding.langchat.aigc.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.data.ReadCellData;
import java.util.Map;

/**
 * @author tycoding
 * @since 2024/4/27
 */
public class StructExcelListener extends AnalysisEventListener<Map<Integer, String>> {

    @Override
    public void invoke(Map<Integer, String> data, AnalysisContext analysisContext) {

        System.out.println("----");
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    @Override
    public void invokeHead(Map<Integer, ReadCellData<?>> headMap, AnalysisContext context) {
        System.out.println("----");
    }
}
