package cn.tycoding.langchat.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.tycoding.langchat.server.common.constant.CacheConst;
import cn.tycoding.langchat.server.common.constant.CommonConst;
import cn.tycoding.langchat.server.common.dto.LcUserInfo;
import cn.tycoding.langchat.server.common.exception.ServiceException;
import cn.tycoding.langchat.server.common.utils.AuthUtil;
import cn.tycoding.langchat.server.common.utils.MybatisUtil;
import cn.tycoding.langchat.server.common.utils.QueryPage;
import cn.tycoding.langchat.server.entity.LcUser;
import cn.tycoding.langchat.server.mapper.UserMapper;
import cn.tycoding.langchat.server.properties.AuthProps;
import cn.tycoding.langchat.server.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tycoding
 * @since 2023/11/15
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, LcUser> implements
        UserService {

    private final AuthProps authProps;

    @Override
    public LcUserInfo findById(Long userId) {
        LcUser lcUser = baseMapper.selectById(userId);
        if (lcUser == null) {
            return null;
        }

        LcUserInfo info = BeanUtil.copyProperties(lcUser, LcUserInfo.class);
        info.setPassword(null);

        return info;
    }

    @Override
    @Cacheable(value = CacheConst.USER_DETAIL_KEY, key = "#username")
    public LcUserInfo info(String username) {
        LcUser lcUser = baseMapper.selectOne(
                Wrappers.<LcUser>lambdaQuery().eq(LcUser::getUsername, username));
        LcUserInfo info = new LcUserInfo();
        BeanUtils.copyProperties(lcUser, info);

        return this.build(info);
    }

    /**
     * 构建用户信息、角色信息、权限标识信息、部门信息
     */
    private LcUserInfo build(LcUserInfo userInfo) {
        if (userInfo == null) {
            throw new ServiceException("Not found user information");
        }

        //    // 获取用户权限列表
        //    List<SysMenu> menuList = new ArrayList<>();
        //    long isAdmin =
        //        sysRoleList.stream().filter(role ->
        // AuthUtil.ADMINISTRATOR.equals(role.getAlias())).count();
        //    if (isAdmin > 0) {
        //      // 包含了超级管理员角色，拥有所有权限
        //      menuList = sysMenuService.list();
        //    } else {
        //      // 根据角色筛选权限
        //      menuList = sysMenuService.getUserMenuList(sysRoleList);
        //    }
        //    Set<String> perms =
        //        menuList.stream()
        //            .map(SysMenu::getPerms)
        //            .filter(StringUtils::isNotEmpty)
        //            .collect(Collectors.toSet());
        //
        //    return userInfo.setRoles(sysRoleList).setPerms(perms).setDept(sysDept);

        return userInfo;
    }

    @Override
    public List<LcUser> list(LcUser lcUser) {
        List<LcUser> list = baseMapper.selectList(
                new LambdaQueryWrapper<LcUser>().ne(LcUser::getUsername, authProps.getAdminName())
                        .like(StringUtils.isNotEmpty(lcUser.getUsername()),
                                LcUser::getUsername, lcUser.getUsername()));
        list.forEach(i -> i.setPassword(null));
        return list;
    }

    @Override
    public IPage<LcUser> page(LcUser lcUser, QueryPage queryPage) {
        return baseMapper.selectPage(MybatisUtil.wrap(lcUser, queryPage),
                Wrappers.<LcUser>lambdaQuery());
    }

    @Override
    public boolean checkName(LcUser lcUser) {
        if (authProps.getAdminName().equals(lcUser.getUsername())) {
            return false;
        }
        LambdaQueryWrapper<LcUser> queryWrapper = new LambdaQueryWrapper<LcUser>().eq(
                LcUser::getUsername, lcUser.getUsername());
        if (lcUser.getId() != null && lcUser.getId() != 0) {
            queryWrapper.ne(LcUser::getId, lcUser.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(LcUser lcUser) {
        if (!checkName(lcUser)) {
            throw new ServiceException("该用户名已存在，请重新输入！");
        }

        lcUser.setCreateTime(new Date());
        lcUser.setPassword(AuthUtil.encode(authProps.getSaltKey(), lcUser.getPassword()));

        // default avatar
        if (StringUtils.isEmpty(lcUser.getAvatar())) {
            lcUser.setAvatar(CommonConst.DEFAULT_AVATAR);
        }

        baseMapper.insert(lcUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConst.USER_DETAIL_KEY, key = "#lcUser.username")
    public void update(LcUser lcUser) {
        if (!checkName(lcUser)) {
            throw new ServiceException("该用户名已存在，请重新输入！");
        }

        // default avatar
        if (StringUtils.isEmpty(lcUser.getAvatar())) {
            lcUser.setAvatar(CommonConst.DEFAULT_AVATAR);
        }
        lcUser.setPassword(null);
        baseMapper.updateById(lcUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConst.USER_DETAIL_KEY, key = "#username")
    public void delete(Long id, String username) {
        baseMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConst.USER_DETAIL_KEY, key = "#username")
    public void reset(Long id, String password, String username) {
        LcUser lcUser = new LcUser();
        lcUser.setId(id);
        lcUser.setPassword(AuthUtil.encode(authProps.getSaltKey(), password));
        baseMapper.updateById(lcUser);
    }
}
