package cn.tycoding.langchat.auth.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.tycoding.langchat.auth.event.LogEvent;
import cn.tycoding.langchat.auth.utils.SysLogUtil;
import cn.tycoding.langchat.common.component.SpringContextHolder;
import cn.tycoding.langchat.common.properties.AuthProps;
import cn.tycoding.langchat.common.utils.R;
import cn.tycoding.langchat.upms.entity.SysLog;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@Configuration
@AllArgsConstructor
public class AuthConfiguration {

    private final AuthProps authProps;
    private final String[] skipUrl = new String[]{
            "/auth/login",
            "/client/auth/login",
            "/client/auth/register/email",
            "/client/auth/code/email",
            "/client/prompt/**",
            "/upload/**",
            "/**",
    };

    @Bean
    public SaServletFilter saServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/favicon.ico")

                .setAuth(obj -> {
                    SaRouter.match("/**")
                            .notMatch(skipUrl)
                            .notMatch(authProps.getSkipUrl().toArray(new String[0]))
                            .check(r -> hasAuth())
                    ;
                })
                .setError(this::handleError);
    }

    private String handleError(Throwable e) {
        System.out.println("---------- 进入Sa-Token异常处理 -----------");

        if (e instanceof NotPermissionException || e instanceof NotRoleException) {
            SysLog sysLog = SysLogUtil.build(SysLogUtil.TYPE_FAIL, HttpStatus.UNAUTHORIZED.getReasonPhrase(), null, null);
            SpringContextHolder.publishEvent(new LogEvent(sysLog));
        }

        // 设置响应头
        SaHolder.getResponse()
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setHeader("Content-Type", "application/json;charset=UTF-8");
        return JSON.toJSONString(R.fail(HttpStatus.UNAUTHORIZED));
    }

    private void hasAuth() {
        StpUtil.checkLogin();

//        // 演示环境禁用操作
//        if (authProps.getIsDemoEnv() && AuthUtil.getRoleNames().contains(AuthUtil.DEMO_ENV)) {
//            throw new ServiceException("演示环境，请勿操作");
//        }
    }
}
