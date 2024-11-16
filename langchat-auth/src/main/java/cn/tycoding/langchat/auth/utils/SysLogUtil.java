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

package cn.tycoding.langchat.auth.utils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.HttpUtil;
import cn.tycoding.langchat.common.auth.event.LogEvent;
import cn.tycoding.langchat.common.core.component.SpringContextHolder;
import cn.tycoding.langchat.upms.entity.SysLog;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.Objects;

/**
 * 构建Log实体类信息
 *
 * @author tycoding
 * @since 2024/1/5
 */
public class SysLogUtil {

    /**
     * 成功日志类型
     */
    public static final int TYPE_OK = 1;
    /**
     * 错误日志类型
     */
    public static final int TYPE_FAIL = 2;

    /**
     * 构建日志Log类信息
     *
     * @param type      日志类型
     * @param operation 日志描述
     * @param method    操作方法
     * @param time      耗时
     * @return Log类
     */
    @SneakyThrows
    public static SysLog build(Integer type, String operation, String method, Long time, String username) {
        HttpServletRequest request = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        return new SysLog()
                .setType(type)
                .setUsername(username)
                .setOperation(operation)
                .setCreateTime(new Date())
                .setIp(JakartaServletUtil.getClientIP(request))
                .setUrl(URLUtil.getPath(request.getRequestURI()))
                .setMethod(method)
                .setParams(HttpUtil.toParams(request.getParameterMap()))
                .setUserAgent(request.getHeader("user-agent"))
                .setTime(time);
    }

    /**
     * Spring事件发布：发布日志，写入到数据库
     *
     * @param type      日志类型
     * @param operation 描述
     */
    public static void publish(int type, String operation, String username) {
        SysLog sysLog = SysLogUtil.build(type, operation, null, null, username);
        SpringContextHolder.publishEvent(new LogEvent(sysLog));
    }
}
