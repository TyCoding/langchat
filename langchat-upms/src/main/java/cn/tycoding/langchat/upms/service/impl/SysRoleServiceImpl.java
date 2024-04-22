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
    public List<SysRole> findRolesByUserId(Long id) {
        return sysUserRoleMapper.getRoleListByUserId(id);
    }

    private List<Long> getMenuIdsByRoleId(Long roleId) {
        List<SysRoleMenu> list = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        return list.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
    }

    @Override
    public SysRoleDTO findById(Long roleId) {
        SysRole role = this.getById(roleId);
        SysRoleDTO sysRole = BeanUtil.copyProperties(role, SysRoleDTO.class);
        sysRole.setMenuIds(getMenuIdsByRoleId(roleId));
        return sysRole;
    }

    @Override
    public void add(SysRoleDTO sysRole) {
        this.save(sysRole);
        addMenus(sysRole);
    }

    @Override
    public void update(SysRoleDTO sysRole) {
        baseMapper.updateById(sysRole);
        addMenus(sysRole);
    }

    private void addMenus(SysRoleDTO sysRole) {
        List<Long> menuIds = sysRole.getMenuIds();
        Long id = sysRole.getId();
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
    public void delete(Long id) {
        SysRole sysRole = this.getById(id);
        if (AuthUtil.ADMINISTRATOR.equals(sysRole.getAlias())) {
            throw new ServiceException("[超级管理员]角色不可删除");
        }
        baseMapper.deleteById(id);
        sysRoleMenuService.deleteRoleMenusByRoleId(id);
        sysUserRoleService.deleteUserRolesByRoleId(id);
    }
}
