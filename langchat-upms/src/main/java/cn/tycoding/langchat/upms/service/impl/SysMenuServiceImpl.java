package cn.tycoding.langchat.upms.service.impl;

import cn.tycoding.langchat.common.constant.CommonConst;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.upms.dto.MenuTree;
import cn.tycoding.langchat.upms.entity.SysMenu;
import cn.tycoding.langchat.upms.entity.SysRole;
import cn.tycoding.langchat.upms.mapper.SysMenuMapper;
import cn.tycoding.langchat.upms.service.SysMenuService;
import cn.tycoding.langchat.upms.service.SysRoleMenuService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import cn.tycoding.langchat.upms.utils.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public List<MenuTree<SysMenu>> tree(SysMenu sysMenu) {
        List<SysMenu> list = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().ne(sysMenu.getId() != null, SysMenu::getId, sysMenu.getId()).eq(sysMenu.getIsDisabled() != null, SysMenu::getIsDisabled, sysMenu.getIsDisabled()));
        return TreeUtil.build(list);
    }

    @Override
//    @Cacheable(value = CacheConst.MENU_DETAIL_KEY, key = "#userId")
    public List<MenuTree<SysMenu>> build(Long userId) {
        List<Long> roleIds = AuthUtil.getRoleIds();
        if (roleIds.isEmpty()) {
//            throw new AuthException(AuthUtil.NOT_ROLE_ERROR);
        }
        if (AuthUtil.getRoleNames().contains(AuthUtil.ADMINISTRATOR)) {
            // 超级管理员，不做权限过滤
            roleIds.clear();
        }
        List<SysMenu> sysMenuList = baseMapper.build(roleIds, CommonConst.MENU_TYPE_MENU);
        List<MenuTree<SysMenu>> build = TreeUtil.build(sysMenuList);
        build.forEach(i -> {
            if (i.getChildren() == null || i.getChildren().isEmpty()) {
                // 对没有children的路由单独处理，前端要求至少有一个children，当children.length=1时自动转换成跟路由
                MenuTree<SysMenu> child = new MenuTree<SysMenu>()
                        .setPath("index")
                        .setName(i.getName())
                        .setComponent(i.getComponent())
                        .setMeta(i.getMeta());
                i.setChildren(Collections.singletonList(child));
                i.setComponent(CommonConst.LAYOUT);
            }
        });
        return build;
    }

    @Override
    public List<SysMenu> getUserMenuList(List<SysRole> sysRoleList) {
        List<Long> roleIds = sysRoleList.stream().map(SysRole::getId).collect(Collectors.toList());
        return baseMapper.build(roleIds, null);
    }

    @Override
    public List<SysMenu> list(SysMenu sysMenu) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().like(sysMenu.getName() != null, SysMenu::getName, sysMenu.getName()).eq(sysMenu.getIsDisabled() != null, SysMenu::getIsDisabled, sysMenu.getIsDisabled()).orderByAsc(SysMenu::getOrderNo));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = CacheConst.MENU_DETAIL_KEY, allEntries = true)
    public void add(SysMenu sysMenu) {
        this.format(sysMenu);
        baseMapper.insert(sysMenu);
    }

    private void format(SysMenu sysMenu) {
        if (CommonConst.MENU_TYPE_MENU.equals(sysMenu.getType())) {
            if (sysMenu.getPath() == null) {
                throw new ServiceException("[path]参数不能为空");
            }
            if (sysMenu.getIcon() == null) sysMenu.setIcon(CommonConst.MENU_ICON);
            if (sysMenu.getIsDisabled() == null) sysMenu.setIsDisabled(false);
            if (sysMenu.getIsExt() == null) sysMenu.setIsExt(false);
            if (sysMenu.getIsKeepalive() == null) sysMenu.setIsKeepalive(true);
            if (sysMenu.getIsShow() == null) sysMenu.setIsShow(true);
            if (sysMenu.getParentId() == null) sysMenu.setParentId(0L);

            if (sysMenu.getParentId() == null || sysMenu.getParentId() == 0) {
                // 父级节点
                if (sysMenu.getComponent() == null) {
                    sysMenu.setComponent(CommonConst.LAYOUT);
                }
                if (!sysMenu.getPath().toLowerCase().startsWith("http") && !sysMenu.getPath().startsWith("/")) {
                    sysMenu.setPath("/" + sysMenu.getPath());
                }
            } else {
                // 子节点
                if (sysMenu.getPath().startsWith("/")) {
                    sysMenu.setPath(sysMenu.getPath().substring(1));
                }
                if (!sysMenu.getIsExt() && !sysMenu.getComponent().startsWith("/")) {
                    sysMenu.setComponent("/" + sysMenu.getComponent());
                }
            }
        }
        if (CommonConst.MENU_TYPE_BUTTON.equals(sysMenu.getType())) {
            sysMenu.setPath(null);
            sysMenu.setIcon(null);
            sysMenu.setComponent(null);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = CacheConst.MENU_DETAIL_KEY, allEntries = true)
    public void update(SysMenu sysMenu) {
        this.format(sysMenu);
        baseMapper.updateById(sysMenu);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
//    @CacheEvict(value = CacheConst.MENU_DETAIL_KEY, allEntries = true)
    public void delete(Long id) {
        List<SysMenu> list = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id));
        if (!list.isEmpty()) {
            throw new ServiceException("该菜单包含子节点，不能删除");
        }
        sysRoleMenuService.deleteRoleMenusByMenuId(id);
        baseMapper.deleteById(id);
    }
}
