package cn.tycoding.langchat.server.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.tycoding.langchat.common.constant.CacheConst;
import cn.tycoding.langchat.common.constant.CommonConst;
import cn.tycoding.langchat.server.dto.LcUserInfo;
import cn.tycoding.langchat.server.exception.ServiceException;
import cn.tycoding.langchat.server.utils.AuthUtil;
import cn.tycoding.langchat.server.utils.MybatisUtil;
import cn.tycoding.langchat.server.utils.QueryPage;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, LcUser> implements UserService {

    private final AuthProps authProps;

    @Override
    public LcUserInfo findById(String userId) {
        LcUser user = baseMapper.selectById(userId);
        if (user == null) {
            return null;
        }

        LcUserInfo info = BeanUtil.copyProperties(user, LcUserInfo.class);
        info.setPassword(null);

        return info;
    }

    @Override
    @Cacheable(value = CacheConst.USER_DETAIL_KEY, key = "#username")
    public LcUserInfo info(String username) {
        LcUser user = baseMapper.selectOne(
                Wrappers.<LcUser>lambdaQuery().eq(LcUser::getUsername, username));
        LcUserInfo info = new LcUserInfo();
        BeanUtils.copyProperties(user, info);

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
    public List<LcUser> list(LcUser user) {
        List<LcUser> list = baseMapper.selectList(
                new LambdaQueryWrapper<LcUser>().ne(LcUser::getUsername, authProps.getAdminName())
                        .like(StringUtils.isNotEmpty(user.getUsername()), LcUser::getUsername,
                                user.getUsername()));
        list.forEach(i -> i.setPassword(null));
        return list;
    }

    @Override
    public IPage<LcUser> page(LcUser user, QueryPage queryPage) {
        return baseMapper.selectPage(MybatisUtil.wrap(user, queryPage),
                Wrappers.<LcUser>lambdaQuery());
    }

    @Override
    public boolean checkName(LcUser user) {
        if (authProps.getAdminName().equals(user.getUsername())) {
            return false;
        }
        LambdaQueryWrapper<LcUser> queryWrapper = new LambdaQueryWrapper<LcUser>().eq(
                LcUser::getUsername, user.getUsername());
        if (user.getId() != null) {
            queryWrapper.ne(LcUser::getId, user.getId());
        }
        return baseMapper.selectList(queryWrapper).isEmpty();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(LcUser user) {
        if (!checkName(user)) {
            throw new ServiceException("该用户名已存在，请重新输入！");
        }

        user.setCreateTime(new Date());
        user.setPassword(AuthUtil.encode(authProps.getSaltKey(), user.getPassword()));

        // default avatar
        if (StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(CommonConst.DEFAULT_AVATAR);
        }

        baseMapper.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConst.USER_DETAIL_KEY, key = "#user.username")
    public void update(LcUser user) {
        if (!checkName(user)) {
            throw new ServiceException("该用户名已存在，请重新输入！");
        }

        // default avatar
        if (StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(CommonConst.DEFAULT_AVATAR);
        }
        user.setPassword(null);
        baseMapper.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConst.USER_DETAIL_KEY, key = "#username")
    public void delete(String id, String username) {
        baseMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConst.USER_DETAIL_KEY, key = "#username")
    public void reset(String id, String password, String username) {
        LcUser user = new LcUser();
        user.setId(id);
        user.setPassword(AuthUtil.encode(authProps.getSaltKey(), password));
        baseMapper.updateById(user);
    }
}
