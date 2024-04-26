package cn.tycoding.langchat.aigc.service;

import cn.tycoding.langchat.aigc.entity.AigcDocs;
import cn.tycoding.langchat.aigc.entity.AigcDocsSlice;
import cn.tycoding.langchat.aigc.entity.AigcKnowledge;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @since 2024/4/15
 */
public interface AigcKnowledgeService extends IService<AigcKnowledge> {

    /**
     * 添加文档数据
     */
    void addDocs(AigcDocs data);

    /**
     * 在指定文档中添加Embedding后的切片数据
     */
    void addDocsSlice(AigcDocsSlice data);
}

