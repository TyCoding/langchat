package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.component.ProviderEnum;
import cn.tycoding.langchat.biz.entity.AigcModel;
import cn.tycoding.langchat.biz.mapper.AigcModelMapper;
import cn.tycoding.langchat.biz.service.AigcModelService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
@RequiredArgsConstructor
public class AigcModelServiceImpl extends ServiceImpl<AigcModelMapper, AigcModel> implements AigcModelService {

    @Override
    public List<AigcModel> getChatModels() {
        return baseMapper.selectList(Wrappers.<AigcModel>lambdaQuery()
                .ne(AigcModel::getProvider, ProviderEnum.EMBEDDING.getModel())
                .ne(AigcModel::getProvider, ProviderEnum.TEXT_IMAGE.getModel()));
    }

    @Override
    public List<AigcModel> getImageModels() {
        return baseMapper.selectList(Wrappers.<AigcModel>lambdaQuery()
                .eq(AigcModel::getProvider, ProviderEnum.TEXT_IMAGE.getModel()));
    }

    @Override
    public List<AigcModel> getEmbeddingModels() {
        return baseMapper.selectList(Wrappers.<AigcModel>lambdaQuery()
                .eq(AigcModel::getProvider, ProviderEnum.EMBEDDING.getModel()));
    }
}

