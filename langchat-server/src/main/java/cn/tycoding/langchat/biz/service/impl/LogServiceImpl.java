package cn.tycoding.langchat.biz.service.impl;

import cn.tycoding.langchat.biz.entity.LcLog;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, LcLog> implements LogService {

    @Override
    public IPage<LcLog> list(LcLog lcLog, QueryPage queryPage) {
        return baseMapper.selectPage(MybatisUtil.wrap(lcLog, queryPage),
                Wrappers.<LcLog>lambdaQuery()
                        .eq(lcLog.getType() != null, LcLog::getType, lcLog.getType())
                        .like(StringUtils.isNotEmpty(lcLog.getOperation()), LcLog::getOperation,
                                lcLog.getOperation()).orderByDesc(LcLog::getCreateTime));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(LcLog lcLog) {
        baseMapper.insert(lcLog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }
}
