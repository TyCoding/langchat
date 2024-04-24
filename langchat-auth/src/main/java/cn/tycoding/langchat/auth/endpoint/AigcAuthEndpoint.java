package cn.tycoding.langchat.auth.endpoint;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.hutool.core.util.StrUtil;
import cn.tycoding.langchat.aigc.entity.AigcUser;
import cn.tycoding.langchat.aigc.service.AigcUserService;
import cn.tycoding.langchat.aigc.utils.AigcStpUtil;
import cn.tycoding.langchat.aigc.utils.AigcUserInfo;
import cn.tycoding.langchat.auth.service.TokenInfo;
import cn.tycoding.langchat.common.constant.CacheConst;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author tycoding
 * @since 2024/1/20
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/aigc/auth")
public class AigcAuthEndpoint {

    private final AigcUserService userService;
    private final AuthProps authProps;

    @PostMapping("/login")
    public R login(@RequestBody AigcUser user) {
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())) {
            throw new ServiceException("Username or password is empty");
        }

        AigcUserInfo userInfo = userService.info(user.getUsername());
        if (userInfo == null) {
            throw new ServiceException("Username or password is not match");
        }

        String decryptPass = AuthUtil.decrypt(authProps.getSaltKey(), userInfo.getPassword());
        if (!decryptPass.equals(user.getPassword())) {
            throw new ServiceException("Username or password is not match");
        }

        AigcStpUtil.login(userInfo.getId());
        SaTokenInfo tokenInfo = AigcStpUtil.getTokenInfo();
        AigcStpUtil.getSession()
                .set(CacheConst.AUTH_USER_INFO_KEY, userInfo)
                .set(CacheConst.AUTH_TOKEN_INFO_KEY, tokenInfo);
        log.info("====> Aigc User Login success! token={}", tokenInfo.getTokenValue());
        return R.ok(
                new TokenInfo().setToken(tokenInfo.tokenValue)
                        .setExpiration(tokenInfo.tokenTimeout));
    }

    @DeleteMapping("/logout")
    public R logout() {
        AigcStpUtil.logout();
        return R.ok();
    }
}
