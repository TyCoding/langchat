package cn.tycoding.langchat.server.service;

import cn.tycoding.langchat.server.entity.LcLog;
import cn.tycoding.langchat.server.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @since 2023/10/19
 */
public interface LogService extends IService<LcLog> {

    /**
     * 分页、条件查询
     */
    IPage<LcLog> list(LcLog lcLog, QueryPage queryPage);

    void add(LcLog lcLog);

    void delete(Long id);
}
