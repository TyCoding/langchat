package cn.tycoding.langchat.biz.service;

import cn.tycoding.langchat.biz.entity.AigcUser;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @since 2024/1/15
 */
public interface AigcUserService extends IService<AigcUser> {

    /**
     * 获取账号登录信息
     */
    AigcUser info(String username);

    /**
     * 校验用户登录信息
     */
    Boolean checkName(AigcUser data);

    /**
     * 账户列表
     */
    IPage<AigcUser> page(AigcUser data, QueryPage queryPage);
}
