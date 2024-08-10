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

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.tycoding.langchat.auth.service.TokenInfo;
import cn.tycoding.langchat.auth.utils.SysLogUtil;
import cn.tycoding.langchat.biz.entity.AigcUser;
import cn.tycoding.langchat.biz.service.AigcUserService;
import cn.tycoding.langchat.biz.utils.ClientAuthUtil;
import cn.tycoding.langchat.biz.utils.ClientStpUtil;
import cn.tycoding.langchat.common.constant.CacheConst;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tycoding
 * @since 2024/1/20
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/client/auth")
public class ClientAuthEndpoint {

    private final AigcUserService userService;
    private final AuthProps props;
    private final StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public R login(@RequestBody AigcUser user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            throw new ServiceException("Username or password is empty");
        }

        AigcUser userInfo = userService.info(user.getUsername());
        if (userInfo == null) {
            throw new ServiceException("Username or password is not match");
        }

        String decryptPass = AuthUtil.decrypt(props.getSaltKey(), userInfo.getPassword());
        if (!decryptPass.equals(user.getPassword())) {
            throw new ServiceException("Username or password is not match");
        }

        ClientStpUtil.login(userInfo.getId());
        SaTokenInfo tokenInfo = ClientStpUtil.getTokenInfo();
        ClientStpUtil.getSession()
                .set(CacheConst.AUTH_USER_INFO_KEY, userInfo)
                .set(CacheConst.AUTH_TOKEN_INFO_KEY, tokenInfo);

        AigcUser aigcUser = BeanUtil.copyProperties(userInfo, AigcUser.class);
        aigcUser.setPassword(null);
        SysLogUtil.publish(1, "客户端登录", ClientAuthUtil.getUsername());
        log.info("====> Aigc User Login success! token={}", tokenInfo.getTokenValue());
        return R.ok(new TokenInfo()
                .setToken(tokenInfo.tokenValue)
                .setExpiration(tokenInfo.tokenTimeout)
                .setUser(aigcUser)
        );
    }

    @GetMapping("/info")
    public R info() {
        AigcUser userInfo = ClientAuthUtil.getUserInfo();
        userInfo.setPassword(null);
        return R.ok(userInfo);
    }

    @DeleteMapping("/logout")
    public R logout() {
        ClientStpUtil.logout();
        return R.ok();
    }

    @PostMapping("/email/register")
    public R emailRegister(@RequestBody AigcUser data) {
        if (StrUtil.isBlank(data.getEmail()) || StrUtil.isBlank(data.getCode()) || StrUtil.isBlank(data.getPassword())) {
            throw new ServiceException("The email address or password is empty");
        }

        // 校验验证码是否正确
        String code = redisTemplate.opsForValue().get(CacheConst.CAPTCHA_PREFIX + data.getEmail());
        if (!data.getCode().equals(code)) {
            throw new ServiceException("Verification code error");
        }

        // 校验用户名是否已存在
        List<AigcUser> list = userService.list(Wrappers.<AigcUser>lambdaQuery().eq(AigcUser::getUsername, data.getEmail()));
        if (!list.isEmpty()) {
            throw new ServiceException("This email address has already been registered");
        }

        AigcUser user = new AigcUser()
                .setUsername(data.getEmail())
                .setPassword(AuthUtil.encode(props.getSaltKey(), data.getPassword()))
                .setNickname(data.getEmail())
                .setEmail(data.getEmail())
                .setChatLimit(props.getChatLimit())
                .setIsPerms(props.getAigcRegUserIsPerms())
                .setStatus(true)
                .setCreateTime(new Date());
        userService.save(user);
        SysLogUtil.publish(1, "客户端注册", user.getUsername());
        return R.ok();
    }

    @GetMapping("/email/code")
    public R getCode(@RequestParam String email) {
        String code = String.valueOf(RandomUtil.randomInt(100000, 999999));
        String template = new ClassPathResource("email/template.html").readUtf8Str();
        String content = StrUtil.format(template, code);
        redisTemplate.opsForValue().set(CacheConst.CAPTCHA_PREFIX + email, code, 10, TimeUnit.MINUTES);

        MailAccount account = new MailAccount()
                .setHost(props.getEmail().getHost())
                .setAuth(true)
                .setPass(props.getEmail().getPass())
                .setFrom(props.getEmail().getFrom())
                .setCharset(StandardCharsets.UTF_8);
        MailUtil.send(account, CollUtil.newArrayList(email), "LangChat Verification code", content, true);
        return R.ok();
    }

    @PostMapping("/forget")
    public R forget(@RequestBody AigcUser data) {
        if (StrUtil.isBlank(data.getEmail()) || StrUtil.isBlank(data.getCode()) || StrUtil.isBlank(data.getPassword())) {
            throw new ServiceException("The email address or password is empty");
        }

        // 校验验证码是否正确
        String code = redisTemplate.opsForValue().get(CacheConst.CAPTCHA_PREFIX + data.getEmail());
        if (!data.getCode().equals(code)) {
            throw new ServiceException("Verification code error");
        }

        // 校验用户名是否已存在
        List<AigcUser> list = userService.list(Wrappers.<AigcUser>lambdaQuery().eq(AigcUser::getUsername, data.getEmail()));
        if (list.isEmpty()) {
            throw new ServiceException("Account does not exist");
        }

        // 重置密码
        AigcUser aigcUser = list.get(0);
        aigcUser.setPassword(AuthUtil.encode(props.getSaltKey(), data.getPassword()));
        userService.updateById(aigcUser);
        return R.ok();
    }
}
