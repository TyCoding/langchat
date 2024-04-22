package cn.tycoding.langchat.upms.mapper;

import cn.tycoding.langchat.upms.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色资源关联表(RoleMenu)表数据库访问层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

}
