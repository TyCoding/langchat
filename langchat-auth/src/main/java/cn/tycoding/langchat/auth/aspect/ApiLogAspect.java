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

package cn.tycoding.langchat.auth.aspect;

import cn.tycoding.langchat.ai.biz.utils.ClientAuthUtil;
import cn.tycoding.langchat.auth.utils.SysLogUtil;
import cn.tycoding.langchat.common.auth.event.LogEvent;
import cn.tycoding.langchat.common.core.annotation.ApiLog;
import cn.tycoding.langchat.common.core.component.SpringContextHolder;
import cn.tycoding.langchat.upms.entity.SysLog;
import cn.tycoding.langchat.upms.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义日志记录切面
 *
 * @author tycoding
 * @since 2024/1/5
 */
@Slf4j
@Aspect
@Configuration
public class ApiLogAspect {

    @Around("@annotation(apiLog)")
    public Object around(ProceedingJoinPoint point, ApiLog apiLog) throws Throwable {
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();

        long beginTime = System.currentTimeMillis();
        long time = System.currentTimeMillis() - beginTime;

        String method = className + "." + methodName + "()";

        String username = null;
        try {
            username = AuthUtil.getUsername();
        } catch (Exception e) {
            username = ClientAuthUtil.getUsername();
        }

        SysLog sysLog = SysLogUtil.build(SysLogUtil.TYPE_OK, apiLog.value(), method, time, username);

        SpringContextHolder.publishEvent(new LogEvent(sysLog));
        return point.proceed();
    }

}
