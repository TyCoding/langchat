package cn.tycoding.langchat.server.endpoint;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.server.common.constant.CacheConst;
import cn.tycoding.langchat.server.common.dto.TokenInfo;
import cn.tycoding.langchat.server.common.dto.LcUserInfo;
import cn.tycoding.langchat.server.common.exception.ServiceException;
import cn.tycoding.langchat.server.common.utils.AuthUtil;
import cn.tycoding.langchat.server.common.utils.QueryPage;
import cn.tycoding.langchat.server.common.utils.R;
import cn.tycoding.langchat.server.properties.AuthProps;
import cn.tycoding.langchat.server.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author tycoding
 * @since 2023/12/20
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthEndpoint {

    private final UserService userService;
    private final AuthProps authProps;
    private final StringRedisTemplate redisTemplate;

    @PostMapping("/login")
    public R login(@RequestBody LcUserInfo user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            throw new ServiceException("Username or password is empty");
        }

        LcUserInfo userInfo = userService.info(user.getUsername());
        if (userInfo == null) {
            throw new ServiceException("Username or password is not match");
        }

        String decryptPass = AuthUtil.decrypt(authProps.getSaltKey(), userInfo.getPassword());
        if (!decryptPass.equals(user.getPassword())) {
            throw new ServiceException("Username or password is not match");
        }

        StpUtil.login(userInfo.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        StpUtil.getSession()
                .set(CacheConst.AUTH_USER_INFO_KEY, userInfo)
                .set(CacheConst.AUTH_TOKEN_INFO_KEY, tokenInfo);
        log.info("====> Login success! token={}", tokenInfo.getTokenValue());
        return R.ok(
                new TokenInfo().setToken(tokenInfo.tokenValue)
                        .setExpiration(tokenInfo.tokenTimeout));
    }

    @DeleteMapping("/logout")
    public R logout() {
        StpUtil.logout();
        return R.ok();
    }

    @DeleteMapping("/token/{token}")
    public R tokenDel(@PathVariable String token) {
        StpUtil.kickoutByTokenValue(token);
        return R.ok();
    }

    @GetMapping("/token/page")
    public R tokenPage(QueryPage queryPage) {
        List<String> list =
                StpUtil.searchTokenValue("", queryPage.getPage(), queryPage.getLimit(), true);
        List ids = redisTemplate.opsForValue().multiGet(list);
        ids.forEach(
                id -> {
                    Map<String, Object> dataMap = StpUtil.getSessionByLoginId(id).getDataMap();
                    //            UserInfo userInfo = (UserInfo)dataMap.get(CacheConst.AUTH_USER_INFO_KEY);
                    //            SaTokenInfo tokenInfo =
                    // (SaTokenInfo)dataMap.get(CacheConst.AUTH_TOKEN_INFO_KEY);
                });

        return R.ok(list);
    }
}
