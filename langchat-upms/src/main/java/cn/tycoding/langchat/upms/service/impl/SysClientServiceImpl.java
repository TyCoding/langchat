package cn.tycoding.langchat.upms.service.impl;

import cn.tycoding.langchat.upms.entity.SysClient;
import cn.tycoding.langchat.upms.mapper.SysClientMapper;
import cn.tycoding.langchat.upms.service.SysClientService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class SysClientServiceImpl extends ServiceImpl<SysClientMapper, SysClient> implements SysClientService {

    @Override
    public SysClient findByName(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<SysClient>().eq(SysClient::getUsername, username));
    }
}
