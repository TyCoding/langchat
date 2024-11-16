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

import cn.dev33.satoken.exception.NotPermissionException;
import cn.tycoding.langchat.ai.biz.entity.AigcUser;
import cn.tycoding.langchat.ai.biz.utils.ClientAuthUtil;
import cn.tycoding.langchat.common.core.annotation.ClientPerm;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义AIGC权限切面
 *
 * @author tycoding
 * @since 2024/7/5
 */
@Slf4j
@Aspect
@Configuration
public class ClientPermAspect {

    @Around("@annotation(clientPerm)")
    public Object around(ProceedingJoinPoint point, ClientPerm clientPerm) throws Throwable {
        AigcUser userInfo = ClientAuthUtil.getUserInfo();
        if (userInfo.getIsPerms() == null || !userInfo.getIsPerms()) {
            throw new NotPermissionException("当前账号没有操作权限，请联系管理员");
        }
        return point.proceed();
    }

}
