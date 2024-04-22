package cn.tycoding.langchat.upms.service.impl;

import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.upms.entity.SysLog;
import cn.tycoding.langchat.upms.mapper.SysLogMapper;
import cn.tycoding.langchat.upms.service.SysLogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统日志表(Log)表服务实现类
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Service
@RequiredArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public IPage<SysLog> list(SysLog sysLog, QueryPage queryPage) {
        return baseMapper.selectPage(MybatisUtil.wrap(sysLog, queryPage),
                Wrappers.<SysLog>lambdaQuery()
                        .eq(sysLog.getType() != null, SysLog::getType, sysLog.getType())
                        .like(StringUtils.isNotEmpty(sysLog.getOperation()), SysLog::getOperation, sysLog.getOperation())
                        .orderByDesc(SysLog::getCreateTime)
        );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SysLog sysLog) {
        baseMapper.insert(sysLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
