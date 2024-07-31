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

package cn.tycoding.langchat.app.endpoint.auth;

import cn.tycoding.langchat.app.store.AppChannelStore;
import cn.tycoding.langchat.common.exception.AuthException;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.ServletUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
@AllArgsConstructor
public class OpenapiAuthAspect {

    private final AppChannelStore channelStore;

    @Around("@annotation(openapiAuth)")
    public Object around(ProceedingJoinPoint point, OpenapiAuth openapiAuth) throws Throwable {
        String authorization = ServletUtil.getAuthorizationToken();

        if (authorization == null) {
            throw new AuthException(401, "Authentication Token invalid");
        }

        try {
            channelStore.isExpired(openapiAuth.value());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
        return point.proceed();
    }

}