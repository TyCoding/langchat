package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.AigcUser;
import cn.tycoding.langchat.biz.mapper.AigcUserMapper;
import cn.tycoding.langchat.biz.service.AigcUserService;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/15
 */
@Service
@RequiredArgsConstructor
public class AigcUserServiceImpl extends ServiceImpl<AigcUserMapper, AigcUser> implements AigcUserService {

    @Override
    public AigcUser info(String username) {
        List<AigcUser> list = baseMapper.selectList(Wrappers.<AigcUser>lambdaQuery().eq(AigcUser::getUsername, username));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Boolean checkName(AigcUser data) {
        List<AigcUser> list = baseMapper.selectList(Wrappers.<AigcUser>lambdaQuery().eq(AigcUser::getUsername, data.getUsername()));
        return list.isEmpty();
    }

    @Override
    public IPage<AigcUser> page(AigcUser data, QueryPage queryPage) {
        return baseMapper.selectPage(new Page<>(queryPage.getPage(), queryPage.getLimit()), Wrappers.lambdaQuery());
    }
}
