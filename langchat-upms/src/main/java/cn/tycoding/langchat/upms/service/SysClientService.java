package cn.tycoding.langchat.upms.service;

import cn.tycoding.langchat.upms.entity.SysClient;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @since 2024/4/15
 */
public interface SysClientService extends IService<SysClient> {

    /**
     * 根据账户名查询
     */
    SysClient findByName(String username);
}
