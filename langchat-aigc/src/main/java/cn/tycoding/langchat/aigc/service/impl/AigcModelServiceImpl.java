package cn.tycoding.langchat.aigc.service.impl;

import cn.tycoding.langchat.aigc.entity.AigcModel;
import cn.tycoding.langchat.aigc.mapper.AigcModelMapper;
import cn.tycoding.langchat.aigc.service.AigcModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
@RequiredArgsConstructor
public class AigcModelServiceImpl extends ServiceImpl<AigcModelMapper, AigcModel> implements AigcModelService {

}

