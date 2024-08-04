/*
 * Copyright (c) 2024 LangChat. TyCoding All Rights Reserved.
 *
 * Licensed under the GNU Affero General Public License, Version 3 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gnu.org/licenses/agpl-3.0.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    public void delete(String id) {
        baseMapper.deleteById(id);
    }
}
