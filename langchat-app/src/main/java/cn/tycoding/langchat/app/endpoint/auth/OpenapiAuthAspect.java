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

import cn.dev33.satoken.exception.NotPermissionException;
import cn.tycoding.langchat.biz.entity.AigcUser;
import cn.tycoding.langchat.biz.utils.ClientAuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class OpenapiAuthAspect {

    @Around("@annotation(openapiAuth)")
    public Object around(ProceedingJoinPoint point, OpenapiAuth openapiAuth) throws Throwable {
        AigcUser userInfo = ClientAuthUtil.getUserInfo();
        if (userInfo.getIsPerms() == null || !userInfo.getIsPerms()) {
            throw new NotPermissionException("当前账号没有操作权限，请联系管理员");
        }
        return point.proceed();
    }

}