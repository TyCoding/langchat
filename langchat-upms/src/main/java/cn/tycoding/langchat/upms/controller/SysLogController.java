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

package cn.tycoding.langchat.upms.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.Dict;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.entity.SysLog;
import cn.tycoding.langchat.upms.service.SysLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 系统日志表(Log)表控制层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upms/log")
public class SysLogController {

    private final SysLogService sysLogService;

    @GetMapping("/page")
    public R<Dict> list(SysLog sysLog, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(sysLogService.list(sysLog, queryPage)));
    }

    @GetMapping("/{id}")
    public R<SysLog> findById(@PathVariable String id) {
        return R.ok(sysLogService.getById(id));
    }

    @DeleteMapping("/{id}")
    @SaCheckPermission("upms:log:delete")
    public R delete(@PathVariable String id) {
        sysLogService.delete(id);
        return R.ok();
    }
}
