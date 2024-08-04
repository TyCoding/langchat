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
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.upms.dto.SysRoleDTO;
import cn.tycoding.langchat.upms.entity.SysRole;
import cn.tycoding.langchat.upms.entity.SysRoleMenu;
import cn.tycoding.langchat.upms.mapper.SysRoleMapper;
import cn.tycoding.langchat.upms.mapper.SysRoleMenuMapper;
import cn.tycoding.langchat.upms.mapper.SysUserRoleMapper;
import cn.tycoding.langchat.upms.service.SysRoleMenuService;
import cn.tycoding.langchat.upms.service.SysRoleService;
import cn.tycoding.langchat.upms.service.SysUserRoleService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色表(Role)表服务实现类
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    private final SysRoleMenuService sysRoleMenuService;
    private final SysUserRoleService sysUserRoleService;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public IPage<SysRole> page(SysRole role, QueryPage queryPage) {
        return baseMapper.selectPage(MybatisUtil.wrap(role, queryPage),
                Wrappers.<SysRole>lambdaQuery()
                        .like(StringUtils.isNotEmpty(role.getName()), SysRole::getName, role.getName())
        );
    }

    @Override
    public List<SysRole> findRolesByUserId(String id) {
        return sysUserRoleMapper.getRoleListByUserId(id);
    }

    private List<String> getMenuIdsByRoleId(String roleId) {
        List<SysRoleMenu> list = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        return list.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
    }

    @Override
    public SysRoleDTO findById(String roleId) {
        SysRole role = this.getById(roleId);
        SysRoleDTO sysRole = BeanUtil.copyProperties(role, SysRoleDTO.class);
        sysRole.setMenuIds(getMenuIdsByRoleId(roleId));
        return sysRole;
    }

    public boolean checkCode(SysRoleDTO data) {
        if (AuthUtil.ADMINISTRATOR.equals(data.getCode()) || AuthUtil.DEFAULT_ROLE.equals(data.getCode())) {
            return false;
        }
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<SysRole>().eq(SysRole::getCode, data.getCode());
        if (data.getId() != null) {
            queryWrapper.ne(SysRole::getId, data.getId());
        }
        return baseMapper.selectList(queryWrapper).size() <= 0;
    }

    @Override
    public void add(SysRoleDTO sysRole) {
        if (!checkCode(sysRole)) {
            throw new ServiceException("该角色已存在");
        }
        this.save(sysRole);
        addMenus(sysRole);
    }

    @Override
    public void update(SysRoleDTO sysRole) {
        checkCode(sysRole);
        baseMapper.updateById(sysRole);
        addMenus(sysRole);
    }

    private void addMenus(SysRoleDTO sysRole) {
        List<String> menuIds = sysRole.getMenuIds();
        String id = sysRole.getId();
        if (menuIds != null) {
            // 先删除原有的关联
            sysRoleMenuService.deleteRoleMenusByRoleId(id);

            // 再新增关联
            List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
            menuIds.forEach(menuId -> sysRoleMenuList.add(new SysRoleMenu()
                    .setMenuId(menuId)
                    .setRoleId(id)));
            sysRoleMenuService.saveBatch(sysRoleMenuList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        SysRole sysRole = this.getById(id);
        if (!checkCode((SysRoleDTO) sysRole)) {
            throw new ServiceException("该角色不可删除");
        }
        baseMapper.deleteById(id);
        sysRoleMenuService.deleteRoleMenusByRoleId(id);
        sysUserRoleService.deleteUserRolesByRoleId(id);
    }
}
