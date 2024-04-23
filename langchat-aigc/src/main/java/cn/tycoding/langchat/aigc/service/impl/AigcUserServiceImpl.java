package cn.tycoding.langchat.aigc.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.tycoding.langchat.aigc.entity.AigcUser;
import cn.tycoding.langchat.aigc.mapper.AigcUserMapper;
import cn.tycoding.langchat.aigc.service.AigcUserService;
import cn.tycoding.langchat.aigc.utils.AigcUserInfo;
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
    public AigcUserInfo info(String username) {
        return null;
    }

    @Override
    public Boolean checkName(AigcUser data) {
        List<AigcUser> list = baseMapper.selectList(Wrappers.<AigcUser>lambdaQuery().eq(AigcUser::getUsername, data.getUsername()));
        return list.isEmpty();
    }

    @Override
    public List<AigcUserInfo> list(AigcUser data) {
        return List.of();
    }

    @Override
    public AigcUserInfo findById(Long id) {
        return null;
    }

    @Override
    public IPage<AigcUserInfo> page(AigcUser data, QueryPage queryPage) {
        IPage<AigcUser> page = baseMapper.selectPage(new Page<>(queryPage.getPage(), queryPage.getLimit()), Wrappers.lambdaQuery());

        IPage<AigcUserInfo> iPage = new Page<>();
        if (!page.getRecords().isEmpty()) {
            List<AigcUserInfo> list = BeanUtil.copyToList(page.getRecords(), AigcUserInfo.class);
            iPage.setRecords(list)
                    .setCurrent(page.getCurrent())
                    .setPages(page.getPages())
                    .setSize(page.getSize())
                    .setTotal(page.getTotal());
        }

        return iPage;
    }
}
