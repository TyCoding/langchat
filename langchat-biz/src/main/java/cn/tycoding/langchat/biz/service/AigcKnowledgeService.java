package cn.tycoding.langchat.biz.service;

import cn.tycoding.langchat.biz.entity.AigcDocs;
import cn.tycoding.langchat.biz.entity.AigcDocsSlice;
import cn.tycoding.langchat.biz.entity.AigcKnowledge;
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

    void updateDocs(AigcDocs data);

    /**
     * 在指定文档中添加Embedding后的切片数据
     */
    void addDocsSlice(AigcDocsSlice data);

    void updateDocsSlice(AigcDocsSlice data);
}

