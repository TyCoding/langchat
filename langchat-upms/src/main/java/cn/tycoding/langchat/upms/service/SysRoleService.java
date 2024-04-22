package cn.tycoding.langchat.upms.service;

import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.upms.dto.SysRoleDTO;
import cn.tycoding.langchat.upms.entity.SysRole;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author tycoding
 * @since 2024/4/15
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页、条件查询
     */
    IPage<SysRole> page(SysRole role, QueryPage queryPage);

    /**
     * 根据用户ID查询其关联的所有角色
     */
    List<SysRole> findRolesByUserId(Long id);

    /**
     * 根据ID查询
     */
    SysRoleDTO findById(Long roleId);

    /**
     * 新增角色
     */
    void add(SysRoleDTO sysRole);

    /**
     * 修改角色
     */
    void update(SysRoleDTO sysRole);

    /**
     * 删除
     */
    void delete(Long id);
}
