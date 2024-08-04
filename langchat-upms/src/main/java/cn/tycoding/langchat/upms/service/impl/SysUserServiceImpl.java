/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.tycoding.langchat.upms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.common.constant.CacheConst;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.upms.dto.UserInfo;
import cn.tycoding.langchat.upms.entity.*;
import cn.tycoding.langchat.upms.mapper.SysUserMapper;
import cn.tycoding.langchat.upms.service.*;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysRoleService sysRoleService;
    private final SysMenuService sysMenuService;
    private final SysDeptService sysDeptService;
    private final SysUserRoleService sysUserRoleService;
    private final AuthProps authProps;

    @Override
    public SysUser findByName(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    public UserInfo findById(String userId) {
        SysUser sysUser = baseMapper.selectById(userId);
        if (sysUser == null) {
            return null;
        }
        UserInfo dto = BeanUtil.copyProperties(sysUser, UserInfo.class);
        dto.setPassword(null);
        List<String> roleIds =
                sysUserRoleService.getRoleListByUserId(userId).stream().map(SysRole::getId).collect(Collectors.toList());
        dto.setRoleIds(roleIds);
        return dto;
    }

    @Override
//    @Cacheable(value = CacheConst.USER_DETAIL_KEY, key = "#username")
    public UserInfo info(String username) {
        SysUser user = this.findByName(username);
        UserInfo userInfo = BeanUtil.copyProperties(user, UserInfo.class);
        return this.build(userInfo);
    }

    /**
     * 构建用户信息、角色信息、权限标识信息、部门信息
     */
    private UserInfo build(UserInfo userInfo) {
        if (userInfo == null) {
            throw new ServiceException(401, "没有查询用用户信息");
        }
        //获取用户角色列表
        List<SysRole> sysRoleList = sysRoleService.findRolesByUserId(userInfo.getId());
        if (!sysRoleList.isEmpty()) {
            //获取用户权限列表
            List<SysMenu> menuList = new ArrayList<>();
            long isAdmin = sysRoleList.stream().filter(role -> AuthUtil.ADMINISTRATOR.equals(role.getCode())).count();
            if (isAdmin > 0) {
                // 包含了超级管理员角色，拥有所有权限
                menuList = sysMenuService.list();
            } else {
                // 根据角色筛选权限
                menuList = sysMenuService.getUserMenuList(sysRoleList);
            }
            Set<String> perms =
                    menuList.stream().map(SysMenu::getPerms).filter(StringUtils::isNotEmpty).collect(Collectors.toSet());

            List<String> roleIds = sysRoleList.stream().map(SysRole::getId).toList();
            userInfo.setRoleIds(roleIds);

            //获取用户部门信息
            SysDept sysDept = sysDeptService.getById(userInfo.getDeptId());
            return userInfo.setRoles(sysRoleList).setPerms(perms).setDept(sysDept);
        }

        return userInfo;
    }

    @Override
    public List<SysUser> list(SysUser sysUser) {
        List<SysUser> list = baseMapper.selectList(new LambdaQueryWrapper<SysUser>().ne(SysUser::getUsername,
                AuthUtil.ADMINISTRATOR).like(StringUtils.isNotEmpty(sysUser.getUsername()), SysUser::getUsername,
                sysUser.getUsername()));
        list.forEach(i -> i.setPassword(null));
        return list;
    }

    @Override
    public IPage<UserInfo> page(UserInfo user, QueryPage queryPage) {
        return baseMapper.page(MybatisUtil.wrap(user, queryPage), user, AuthUtil.getUserId(), AuthUtil.ADMINISTRATOR);
    }

    @Override
    public boolean checkName(UserInfo sysUser) {
        if (AuthUtil.ADMINISTRATOR.equals(sysUser.getUsername())) {
            return false;
        }
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername,
                sysUser.getUsername());
        if (sysUser.getId() != null) {
            queryWrapper.ne(SysUser::getId, sysUser.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserInfo user) {
        if (!checkName(user)) {
            throw new ServiceException("该用户名已存在，请重新输入！");
        }
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            throw new ServiceException("用户名或密码为空，请重新输入！");
        }

        user.setCreateTime(new Date());
        user.setPassword(AuthUtil.encode(authProps.getSaltKey(), user.getPassword()));

        // 设置角色
        if (user.getRoleIds() == null || user.getRoleIds().isEmpty()) {
            throw new ServiceException("用户角色不能为空");
        }
        baseMapper.insert(user);
        addRole(user);
    }

    private void addRole(UserInfo user) {
        List<String> roleIds = user.getRoleIds();
        String userId = user.getId();
        if (roleIds != null) {
            // 删除之前用户与角色表之前的关联，并重新建立关联
            sysUserRoleService.deleteUserRolesByUserId(userId);

            // 新增用户角色关联
            List<SysUserRole> list = new ArrayList<>();
            roleIds.forEach(roleId -> list.add(new SysUserRole().setUserId(userId).setRoleId(roleId)));
            sysUserRoleService.saveBatch(list);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConst.USER_DETAIL_KEY, key = "#user.username")
    public void update(UserInfo user) {
        if (user.getUsername().equals(AuthUtil.ADMINISTRATOR)) {
            throw new ServiceException("超级管理员用户不可操作");
        }
        if (!checkName(user)) {
            throw new ServiceException("该用户名以存在，请重新输入");
        }
        user.setPassword(null);
        baseMapper.updateById(user);
        addRole(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConst.USER_DETAIL_KEY, key = "#username")
    public void delete(String id, String username) {
        if (username.equals(AuthUtil.ADMINISTRATOR)) {
            throw new ServiceException("超级管理员用户不可操作");
        }
        baseMapper.deleteById(id);
        sysUserRoleService.deleteUserRolesByUserId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = CacheConst.USER_DETAIL_KEY, key = "#username")
    public void reset(String id, String password, String username) {
        if (username.equals(AuthUtil.ADMINISTRATOR)) {
            throw new ServiceException("超级管理员用户不可操作");
        }
        SysUser user = new SysUser();
        user.setId(id);
        user.setPassword(AuthUtil.encode(authProps.getSaltKey(), password));
        baseMapper.updateById(user);
    }
}
