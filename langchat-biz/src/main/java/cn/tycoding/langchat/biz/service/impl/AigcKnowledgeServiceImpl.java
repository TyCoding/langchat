package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.AigcDocs;
import cn.tycoding.langchat.biz.entity.AigcDocsSlice;
import cn.tycoding.langchat.biz.entity.AigcKnowledge;
import cn.tycoding.langchat.biz.mapper.AigcDocsMapper;
import cn.tycoding.langchat.biz.mapper.AigcDocsSliceMapper;
import cn.tycoding.langchat.biz.mapper.AigcKnowledgeMapper;
import cn.tycoding.langchat.biz.service.AigcKnowledgeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class AigcKnowledgeServiceImpl extends ServiceImpl<AigcKnowledgeMapper, AigcKnowledge> implements AigcKnowledgeService {

    private final AigcDocsMapper aigcDocsMapper;
    private final AigcDocsSliceMapper aigcDocsSliceMapper;

    @Override
    public void addDocs(AigcDocs data) {
        data.setCreateTime(new Date());
        aigcDocsMapper.insert(data);
    }

    @Override
    public void updateDocs(AigcDocs data) {
        aigcDocsMapper.updateById(data);
    }

    @Override
    public void addDocsSlice(AigcDocsSlice data) {
        data.setCreateTime(new Date())
                .setWordNum(data.getContent().length())
                .setStatus(true)
        ;
        aigcDocsSliceMapper.insert(data);
    }

    @Override
    public void updateDocsSlice(AigcDocsSlice data) {
        aigcDocsSliceMapper.updateById(data);
    }
}

