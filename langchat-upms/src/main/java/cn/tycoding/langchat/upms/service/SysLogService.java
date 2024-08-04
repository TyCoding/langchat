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
    void delete(String id);
}
