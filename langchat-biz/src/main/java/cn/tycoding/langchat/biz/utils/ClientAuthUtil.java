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

package cn.tycoding.langchat.biz.utils;

import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.tycoding.langchat.biz.entity.AigcUser;
import cn.tycoding.langchat.common.constant.CacheConst;
import cn.tycoding.langchat.common.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * 权限相关方法
 *
 * @author tycoding
 * @since 2024/4/15
 */
public class ClientAuthUtil {

    /**
     * 获取Request对象
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    /**
     * 获取Response对象
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    /**
     * 截取前端Token字符串中不包含`Bearer`的部分
     */
    public static String getToken(String token) {
        if (token != null && token.toLowerCase().startsWith("bearer")) {
            return token.replace("bearer", "").trim();
        }
        return token;
    }

    /**
     * 获取用户数据
     */
    public static AigcUser getUserInfo() {
        try {
            return (AigcUser) ClientStpUtil.getSession().get(CacheConst.AUTH_USER_INFO_KEY);
        } catch (Exception e) {
            if (e instanceof NotPermissionException) {
                throw new AuthException(403, "没有操作权限");
            }
            throw new AuthException(403, "登录已失效，请重新登陆");
        }
    }

    /**
     * 获取用户名
     */
    public static String getUsername() {
        AigcUser userInfo = getUserInfo();
        if (userInfo == null) {
            return null;
        }
        return userInfo.getUsername();
    }

    /**
     * 获取登录用户ID
     */
    public static String getUserId() {
        AigcUser userInfo = getUserInfo();
        if (userInfo == null) {
            return null;
        }
        return userInfo.getId();
    }

    /**
     * 密码加密
     */
    public static String encode(String salt, String password) {
        return SaSecureUtil.aesEncrypt(salt, password);
    }

    /**
     * 密码解密
     */
    public static String decrypt(String salt, String password) {
        return SaSecureUtil.aesDecrypt(salt, password);
    }

    public static void main(String[] args) {
        System.out.println(encode("langchat-salt", "123456"));
    }
}
