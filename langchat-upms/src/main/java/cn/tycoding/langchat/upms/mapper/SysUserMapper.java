package cn.tycoding.langchat.upms.mapper;

import cn.tycoding.langchat.upms.dto.UserInfo;
import cn.tycoding.langchat.upms.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(User)表数据库访问层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    IPage<UserInfo> page(IPage<SysUser> page, UserInfo user, Long ignoreId, String ignoreName);
}
