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
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.dto.SysRoleDTO;
import cn.tycoding.langchat.upms.entity.SysRole;
import cn.tycoding.langchat.upms.service.SysRoleService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色表(Role)表控制层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upms/role")
public class SysRoleController {

    private final SysRoleService sysRoleService;

    @GetMapping("/list")
    public R<List<SysRole>> list(SysRole sysRole) {
        return R.ok(sysRoleService.list(new LambdaQueryWrapper<SysRole>()
                .ne(SysRole::getCode, AuthUtil.ADMINISTRATOR)));
    }

    @GetMapping("/page")
    public R<Dict> page(SysRole role, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(sysRoleService.page(role, queryPage)));
    }

    @GetMapping("/{id}")
    public R<SysRoleDTO> findById(@PathVariable String id) {
        return R.ok(sysRoleService.findById(id));
    }

    @PostMapping
    @ApiLog("新增角色")
    @SaCheckPermission("upms:role:add")
    public R add(@RequestBody SysRoleDTO sysRole) {
        sysRoleService.add(sysRole);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改角色")
    @SaCheckPermission("upms:role:update")
    public R update(@RequestBody SysRoleDTO sysRole) {
        sysRoleService.update(sysRole);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除角色")
    @SaCheckPermission("upms:role:delete")
    public R delete(@PathVariable String id) {
        sysRoleService.delete(id);
        return R.ok();
    }
}
