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
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.dto.UserInfo;
import cn.tycoding.langchat.upms.entity.SysUser;
import cn.tycoding.langchat.upms.service.SysUserService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户表(User)表控制层
 *
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upms/user")
public class SysUserController {

    private final SysUserService sysUserService;
    private final AuthProps authProps;

    @GetMapping("/checkName")
    public R<Boolean> checkName(UserInfo sysUser) {
        return R.ok(sysUserService.checkName(sysUser));
    }

    @GetMapping("/list")
    public R<List<SysUser>> list(SysUser sysUser) {
        return R.ok(sysUserService.list(sysUser));
    }

    @GetMapping("/page")
    public R<Dict> page(UserInfo user, QueryPage queryPage) {
        return R.ok(MybatisUtil.getData(sysUserService.page(user, queryPage)));
    }

    @GetMapping("/{id}")
    public R<UserInfo> findById(@PathVariable String id) {
        return R.ok(sysUserService.findById(id));
    }

    @PostMapping
    @ApiLog("新增用户")
    @SaCheckPermission("upms:user:add")
    public R<SysUser> add(@RequestBody UserInfo user) {
        sysUserService.add(user);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改用户")
    @SaCheckPermission("upms:user:update")
    public R update(@RequestBody UserInfo user) {
        sysUserService.update(user);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除用户")
    @SaCheckPermission("upms:user:delete")
    public R delete(@PathVariable String id) {
        SysUser user = sysUserService.getById(id);
        if (user != null) {
            sysUserService.delete(id, user.getUsername());
        }
        return R.ok();
    }

    @PutMapping("/resetPass")
    @ApiLog("重置密码")
    @SaCheckPermission("upms:user:reset")
    public R resetPass(@RequestBody UserInfo data) {
        SysUser user = sysUserService.getById(data.getId());
        if (user != null) {
            sysUserService.reset(data.getId(), data.getPassword(), user.getUsername());
        }
        return R.ok();
    }

    @PutMapping("/updatePass")
    @ApiLog("修改密码")
    @SaCheckPermission("upms:user:updatePass")
    public R updatePass(@RequestBody UserInfo data) {
        SysUser user = sysUserService.getById(data.getId());
        if (user == null || !AuthUtil.decrypt(authProps.getSaltKey(), user.getPassword()).equals(data.getPassword())) {
            throw new ServiceException("Old password entered incorrectly, please re-enter");
        }
        user.setPassword(AuthUtil.encode(authProps.getSaltKey(), data.getPassword()));
        return R.ok();
    }
}
