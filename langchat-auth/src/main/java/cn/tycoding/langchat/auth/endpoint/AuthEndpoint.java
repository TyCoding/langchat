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

package cn.tycoding.langchat.auth.endpoint;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.auth.service.TokenInfo;
import cn.tycoding.langchat.auth.utils.SysLogUtil;
import cn.tycoding.langchat.common.constant.CacheConst;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.utils.MybatisUtil;
import cn.tycoding.langchat.common.utils.QueryPage;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.dto.UserInfo;
import cn.tycoding.langchat.upms.entity.SysRole;
import cn.tycoding.langchat.upms.entity.SysUser;
import cn.tycoding.langchat.upms.service.SysRoleService;
import cn.tycoding.langchat.upms.service.SysUserService;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static cn.tycoding.langchat.common.constant.CacheConst.AUTH_SESSION_PREFIX;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthEndpoint {

    private final SysUserService userService;
    private final SysRoleService roleService;
    private final AuthProps authProps;
    private final StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public R login(@RequestBody UserInfo user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            throw new ServiceException("The user name or password is empty");
        }

        UserInfo userInfo = userService.info(user.getUsername());
        if (userInfo == null) {
            throw new ServiceException("The username or password is error");
        }

        String decryptPass = AuthUtil.decrypt(authProps.getSaltKey(), userInfo.getPassword());
        if (!decryptPass.equals(user.getPassword())) {
            throw new ServiceException("The username or password is error");
        }

        StpUtil.login(userInfo.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        StpUtil.getSession()
                .set(CacheConst.AUTH_USER_INFO_KEY, userInfo)
                .set(CacheConst.AUTH_TOKEN_INFO_KEY, tokenInfo);
        SysLogUtil.publish(1, "服务端登录", AuthUtil.getUsername());
        log.info("====> login success，token={}", tokenInfo.getTokenValue());
        return R.ok(new TokenInfo().setToken(tokenInfo.tokenValue).setExpiration(tokenInfo.tokenTimeout));
    }

    @DeleteMapping("/logout")
    public R logout() {
        StpUtil.logout();
        return R.ok();
    }

    @PostMapping("/register")
    public R emailRegister(@RequestBody SysUser data) {
        if (StrUtil.isBlank(data.getUsername()) || StrUtil.isBlank(data.getPassword())) {
            throw new ServiceException("用户名或密码为空");
        }

        // 校验用户名是否已存在
        List<SysUser> list = userService.list(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, data.getUsername()));
        if (!list.isEmpty()) {
            throw new ServiceException("该用户名已存在");
        }

        List<SysRole> roles = roleService.list(Wrappers.<SysRole>lambdaQuery().eq(SysRole::getCode, AuthUtil.DEFAULT_ROLE));
        if (roles.isEmpty()) {
            throw new ServiceException("系统角色配置异常，请联系管理员");
        }

        UserInfo user = (UserInfo) new UserInfo()
                .setRoleIds(roles.stream().map(SysRole::getId).toList())
                .setUsername(data.getUsername())
                .setPassword(AuthUtil.encode(authProps.getSaltKey(), data.getPassword()))
                .setRealName(data.getUsername())
                .setPhone(data.getPhone())
                .setStatus(true)
                .setCreateTime(new Date());
        userService.add(user);
        SysLogUtil.publish(1, "服务端注册", AuthUtil.getUsername());
        return R.ok();
    }

    @GetMapping("/info")
    public R<UserInfo> info() {
        UserInfo userInfo = userService.info(AuthUtil.getUsername());
        userInfo.setPassword(null);
        return R.ok(userInfo);
    }

    @DeleteMapping("/token/{token}")
    @SaCheckPermission("auth:delete")
    public R tokenDel(@PathVariable String token) {
        StpUtil.kickoutByTokenValue(token);
        return R.ok();
    }

    @GetMapping("/token/page")
    public R tokenPage(QueryPage queryPage) {
        List<String> list = StpUtil.searchTokenValue("", queryPage.getPage() - 1, queryPage.getLimit(), true);
        List ids = redisTemplate.opsForValue().multiGet(list);
        Set<String> keys = redisTemplate.keys(AUTH_SESSION_PREFIX + "*");

        List<Object> result = new ArrayList<>();
        ids.forEach(id -> {
            Dict data = Dict.create();
            Map<String, Object> dataMap = StpUtil.getSessionByLoginId(id).getDataMap();
            UserInfo userInfo = (UserInfo)dataMap.get(CacheConst.AUTH_USER_INFO_KEY);
            if (userInfo == null || Objects.equals(AuthUtil.getUserId(), userInfo.getId())) {
                return;
            }
            SaTokenInfo tokenInfo = (SaTokenInfo)dataMap.get(CacheConst.AUTH_TOKEN_INFO_KEY);
            if (tokenInfo == null) {
                return;
            }
            data.set("token", tokenInfo.tokenValue);
            data.set("perms", userInfo.getPerms());
            data.set("roles", userInfo.getRoles());
            data.set("email", userInfo.getEmail());
            data.set("id", userInfo.getId());
            data.set("username", userInfo.getUsername());
            data.set("realName", userInfo.getRealName());

            long expiration = StpUtil.getTokenTimeout();
            Date targetDate = new Date(System.currentTimeMillis() + expiration);
            String formattedDate = DateUtil.format(targetDate, DatePattern.NORM_DATETIME_PATTERN);
            data.set("expiration", formattedDate);

            result.add(data);
        });

        IPage page = new Page(queryPage.getPage(), queryPage.getLimit());
        page.setRecords(result);
        page.setTotal(keys == null ? 0 : keys.size());
        return R.ok(MybatisUtil.getData(page));
    }

}
