package cn.tycoding.langchat.biz.service;

import cn.tycoding.langchat.biz.dto.UserInfo;
import cn.tycoding.langchat.biz.entity.SysUser;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/15
 */
public interface UserService extends IService<SysUser> {

    UserInfo findById(String userId);

    UserInfo info(String username);

    List<SysUser> list(SysUser user);

    IPage<SysUser> page(SysUser user, QueryPage queryPage);

    boolean checkName(SysUser user);

    void add(SysUser user);

    void update(SysUser user);

    void delete(String id, String username);

    void reset(String id, String password, String username);
}
