package cn.tycoding.langchat.biz.service;

import cn.tycoding.langchat.biz.entity.LcLog;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author tycoding
 * @since 2024/1/19
 */
public interface LogService extends IService<LcLog> {

    /**
     * 分页、条件查询
     */
    IPage<LcLog> list(LcLog lcLog, QueryPage queryPage);

    void add(LcLog lcLog);

    void delete(Long id);
}
