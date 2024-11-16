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

package cn.tycoding.langchat.common.auth.interceptor;

import cn.tycoding.langchat.common.core.constant.AuthConst;
import cn.tycoding.langchat.common.core.constant.CacheConst;
import cn.tycoding.langchat.common.core.utils.R;
import cn.tycoding.langchat.common.core.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@Slf4j
@AllArgsConstructor
public class CaptchaInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if ("/oauth/token".equals(request.getRequestURI())) {
            String headerKey = request.getHeader(AuthConst.CAPTCHA_HEADER_KEY);
            if (headerKey == null) {
                // 特殊处理，对于类似Swagger中直接获取认证Token时不带验证码
                log.info("正在进行请求授权，未携带Captcha-Key请求头，不进行验证码校验");
                return true;
            }

            String code = ServletRequestUtils.getStringParameter(request, AuthConst.CAPTCHA_FORM_KEY);
            String redisCode = (String) redisTemplate.opsForValue().get(CacheConst.CAPTCHA_PREFIX + headerKey);
            if (code == null || !code.toLowerCase().equals(redisCode)) {
                ServletUtil.write(response, new R<>(HttpStatus.BAD_REQUEST.value(), AuthConst.CAPTCHA_ERROR_INFO));
                return false;
            }
        }
        return true;
    }
}
