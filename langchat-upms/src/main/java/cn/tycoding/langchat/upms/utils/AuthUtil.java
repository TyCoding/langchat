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

package cn.tycoding.langchat.upms.utils;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.tycoding.langchat.common.constant.CacheConst;
import cn.tycoding.langchat.common.exception.AuthException;
import cn.tycoding.langchat.upms.dto.UserInfo;
import cn.tycoding.langchat.upms.entity.SysRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 权限相关方法
 *
 * @author tycoding
 * @since 2024/4/15
 */
public class AuthUtil {

    /**
     * 系统预制固定超级管理员角色别名
     * 作用：提供一个角色摆脱权限体系的控制，允许词角色访问所有菜单权限
     * 使用：所有涉及根据角色查询的地方都排除对此角色的限制
     */
    public static final String ADMINISTRATOR = "administrator";
    public static final String DEMO_ROLE = "demo_env";
    public static final String DEFAULT_ROLE = "default_env";

    /* 登录表单验证码Key标识 */
    public static final String CAPTCHA_FORM_KEY = "captcha";
    /* 登录验证码Header Key标识 */
    public static final String CAPTCHA_HEADER_KEY = "Captcha-Key";
    /* 验证码错误信息 */
    public static final String CAPTCHA_ERROR_INFO = "验证码不正确";
    /* 没有查询到用户名 */
    public static final String NOT_ROLE_ERROR = "没有查询到用户角色信息";

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
    public static UserInfo getUserInfo() {
        try {
            return (UserInfo) StpUtil.getSession().get(CacheConst.AUTH_USER_INFO_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthException(403, "登录已失效，请重新登陆");
        }
    }
    public static boolean isDemoEnv() {
        try {
            UserInfo info =  (UserInfo) StpUtil.getSession().get(CacheConst.AUTH_USER_INFO_KEY);
            List<SysRole> roles = info.getRoles();
            if (roles != null && !roles.isEmpty()) {
                List<SysRole> list = roles.stream().filter(i -> i.getCode().equals(DEMO_ROLE)).toList();
                return !list.isEmpty();
            }
            return true;
        } catch (Exception ignored) {
            return true;
        }
    }

    /**
     * 获取用户名
     */
    public static String getUsername() {
        UserInfo userInfo = getUserInfo();
        if (userInfo == null) {
            return null;
        }
        return userInfo.getUsername();
    }

    /**
     * 获取登录用户ID
     */
    public static String getUserId() {
        UserInfo userInfo = getUserInfo();
        if (userInfo == null) {
            return null;
        }
        return userInfo.getId();
    }

    /**
     * 获取用户角色Id集合
     */
    public static List<String> getRoleIds() {
        UserInfo userInfo = getUserInfo();
        if (userInfo == null || userInfo.getRoleIds() == null) {
            return new ArrayList<>();
        }
        return userInfo.getRoleIds();
    }

    /**
     * 获取用户角色Alias集合
     */
    public static List<String> getRoleNames() {
        UserInfo userInfo = getUserInfo();
        if (userInfo == null || userInfo.getRoles() == null) {
            return new ArrayList<>();
        }
        return userInfo.getRoles().stream().map(SysRole::getCode).toList();
    }

    /**
     * 获取权限集合
     */
    public static List<String> getPermissionNames() {
        UserInfo userInfo = getUserInfo();
        if (userInfo == null || userInfo.getPerms() == null) {
            return new ArrayList<>();
        }
        return userInfo.getPerms().stream().toList();
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
