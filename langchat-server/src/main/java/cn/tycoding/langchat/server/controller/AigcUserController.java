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

package cn.tycoding.langchat.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.biz.entity.AigcUser;
import cn.tycoding.langchat.biz.service.AigcUserService;
import cn.tycoding.langchat.biz.utils.ClientStpUtil;
import cn.tycoding.langchat.common.annotation.ApiLog;
import cn.tycoding.langchat.common.constant.CacheConst;
import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author tycoding
 * @since 2024/4/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/aigc/user")
public class AigcUserController {

    private final AigcUserService userService;
    private final AuthProps authProps;

    @GetMapping("/info")
    public R<AigcUser> info() {
        AigcUser userInfo = userService.info(AuthUtil.getUsername());
        userInfo.setPassword(null);
        return R.ok(userInfo);
    }

    @GetMapping("/checkName")
    public R<Boolean> checkName(AigcUser sysUser) {
        return R.ok(userService.checkName(sysUser));
    }

    @GetMapping("/list")
    public R<List<AigcUser>> list(AigcUser data) {
        List<AigcUser> list = userService.list(Wrappers.lambdaQuery());
        list.forEach(i -> i.setPassword(null));
        return R.ok(list);
    }

    @GetMapping("/page")
    public R<Dict> page(AigcUser user, QueryPage queryPage) {
        IPage<AigcUser> page = userService.page(user, queryPage);
        page.getRecords().forEach(i -> i.setPassword(null));
        return R.ok(MybatisUtil.getData(page));
    }

    @GetMapping("/{id}")
    public R<AigcUser> findById(@PathVariable String id) {
        AigcUser user = userService.getById(id);
        user.setPassword(null);
        return R.ok(user);
    }

    @PostMapping
    @ApiLog("新增客户端用户")
    @SaCheckPermission("aigc:user:add")
    public R<AigcUser> add(@RequestBody AigcUser data) {
        data.setChatLimit(authProps.getChatLimit());
        data.setPassword(AuthUtil.encode(authProps.getSaltKey(), data.getPassword()));
        userService.save(data);
        return R.ok();
    }

    @PutMapping
    @ApiLog("修改客户端用户")
    @SaCheckPermission("aigc:user:update")
    public R update(@RequestBody AigcUser data) {
        if (StrUtil.isNotBlank(data.getPassword())) {
            data.setPassword(AuthUtil.encode(authProps.getSaltKey(), data.getPassword()));
        }
        userService.updateById(data);
        ClientStpUtil.getSessionByLoginId(data.getId()).set(CacheConst.AUTH_USER_INFO_KEY, data);
        ClientStpUtil.getSessionByLoginId(data.getId()).update();
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @ApiLog("删除客户端用户")
    @SaCheckPermission("aigc:user:delete")
    public R delete(@PathVariable String id) {
        AigcUser user = userService.getById(id);
        if (user != null) {
            userService.removeById(id);
        }
        return R.ok();
    }
}
