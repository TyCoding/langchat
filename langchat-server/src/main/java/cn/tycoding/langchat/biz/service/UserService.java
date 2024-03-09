package cn.tycoding.langchat.biz.service;

import cn.tycoding.langchat.biz.dto.LcUserInfo;
import cn.tycoding.langchat.biz.entity.LcUser;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/15
 */
public interface UserService extends IService<LcUser> {

    LcUserInfo findById(String userId);

    LcUserInfo info(String username);

    List<LcUser> list(LcUser user);

    IPage<LcUser> page(LcUser user, QueryPage queryPage);

    boolean checkName(LcUser user);

    void add(LcUser user);

    void update(LcUser user);

    void delete(String id, String username);

    void reset(String id, String password, String username);
}
