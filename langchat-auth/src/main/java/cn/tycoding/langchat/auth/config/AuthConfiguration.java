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

package cn.tycoding.langchat.auth.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.router.SaRouter;
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
            "/aigc/auth/**",
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
                    ;
                })
                .setError(this::handleError);
    }

    private String handleError(Throwable e) {
        if (e instanceof NotPermissionException || e instanceof NotRoleException) {
            SysLog sysLog = SysLogUtil.build(SysLogUtil.TYPE_FAIL, HttpStatus.UNAUTHORIZED.getReasonPhrase(), null, null);
            SpringContextHolder.publishEvent(new LogEvent(sysLog));
        }

        SaHolder.getResponse()
                .setStatus(HttpStatus.UNAUTHORIZED.value())
                .setHeader("Content-Type", "application/json;charset=UTF-8");
        return JSON.toJSONString(R.fail(HttpStatus.UNAUTHORIZED));
    }
}
