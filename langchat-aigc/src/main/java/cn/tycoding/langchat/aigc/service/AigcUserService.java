package cn.tycoding.langchat.aigc.service;

import cn.tycoding.langchat.aigc.entity.AigcUser;
import cn.tycoding.langchat.aigc.utils.AigcUserInfo;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/1/15
 */
public interface AigcUserService extends IService<AigcUser> {

    /**
     * 获取账号登录信息
     */
    AigcUserInfo info(String username);

    /**
     * 校验用户登录信息
     */
    Boolean checkName(AigcUser data);

    /**
     * 账户列表
     */
    List<AigcUserInfo> list(AigcUser data);

    /**
     * 获取账号信息
     */
    AigcUserInfo findById(Long id);

    /**
     * 账户列表
     */
    IPage<AigcUserInfo> page(AigcUser data, QueryPage queryPage);
}
