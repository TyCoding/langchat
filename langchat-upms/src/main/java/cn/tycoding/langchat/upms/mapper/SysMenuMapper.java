package cn.tycoding.langchat.upms.mapper;

import cn.tycoding.langchat.upms.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单表(Menu)表数据库访问层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> build(@Param("roleIds") List<Long> roleIds, @Param("type") String type);
}
