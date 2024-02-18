package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.server.common.dto.LcUserInfo;
import cn.tycoding.langchat.server.common.utils.QueryPage;
import cn.tycoding.langchat.server.entity.LcUser;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @since 2023/11/15
 */
public interface UserService extends IService<LcUser> {

    LcUserInfo findById(Long userId);

    LcUserInfo info(String username);

    List<LcUser> list(LcUser lcUser);

    IPage<LcUser> page(LcUser lcUser, QueryPage queryPage);

    boolean checkName(LcUser lcUser);

    void add(LcUser lcUser);

    void update(LcUser lcUser);

    void delete(Long id, String username);

    void reset(Long id, String password, String username);
}
