package cn.tycoding.langchat.upms.service;

import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.upms.entity.SysLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统日志表(Log)表服务接口
 *
 * @author tycoding
 * @since 2024/4/15
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 分页、条件查询
     */
    IPage<SysLog> list(SysLog sysLog, QueryPage queryPage);

    /**
     * 新增
     */
    void add(SysLog sysLog);
    /**
     * 删除
     */
    void delete(Long id);
}
