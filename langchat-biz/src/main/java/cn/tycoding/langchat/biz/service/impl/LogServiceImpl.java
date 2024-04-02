package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.SysLog;
import cn.tycoding.langchat.biz.mapper.LogMapper;
import cn.tycoding.langchat.biz.service.LogService;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tycoding
 * @since 2024/1/19
 */
@Service
@RequiredArgsConstructor
public class LogServiceImpl extends ServiceImpl<LogMapper, SysLog> implements LogService {

    @Override
    public IPage<SysLog> list(SysLog sysLog, QueryPage queryPage) {
        return baseMapper.selectPage(MybatisUtil.wrap(sysLog, queryPage),
                Wrappers.<SysLog>lambdaQuery()
                        .eq(sysLog.getType() != null, SysLog::getType, sysLog.getType())
                        .like(StringUtils.isNotEmpty(sysLog.getOperation()), SysLog::getOperation,
                                sysLog.getOperation()).orderByDesc(SysLog::getCreateTime));
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
