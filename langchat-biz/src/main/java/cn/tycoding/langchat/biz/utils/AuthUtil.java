package cn.tycoding.langchat.biz.utils;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.tycoding.langchat.biz.dto.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author tycoding
 * @since 2024/1/15
 */
public class AuthUtil {

    /* 登录表单验证码Key标识 */
    public static final String CAPTCHA_FORM_KEY = "captcha";
    /* 登录验证码Header Key标识 */
    public static final String CAPTCHA_HEADER_KEY = "Captcha-Key";

    /**
     * get request object
     */
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
    }

    /**
     * get response object
     */
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getResponse();
    }

    /**
     * split str from authorization
     */
    public static String getToken(String token) {
        if (token != null && token.toLowerCase().startsWith("bearer")) {
            return token.replace("bearer", "").trim();
        }
        return token;
    }

    /**
     * get user info
     */
    public static UserInfo getUserInfo() {
        try {
//            return (LcUserInfo) StpUtil.getSession().get(CacheConst.AUTH_USER_INFO_KEY);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * get username
     */
    public static String getUsername() {
        UserInfo userInfo = getUserInfo();
        if (userInfo == null) {
            return null;
        }
        return userInfo.getUsername();
    }

    /**
     * get user id
     */
    public static String getUserId() {
        UserInfo userInfo = getUserInfo();
        if (userInfo == null) {
            return null;
        }
        return userInfo.getId();
    }

    /**
     * get user roles
     */
    public static List<Long> getRoleIds() {
        //        List<Long> roleIds = new ArrayList<>();
        //        Authentication authentication = getAuthentication();
        //        if (authentication == null) {
        //            return roleIds;
        //        }
        //        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //        authorities.stream().filter(granted -> StringUtils.startsWith(granted.getAuthority(),
        // AuthConstant.ROLE_PREFIX)).forEach(granted -> {
        //            String id = StringUtils.substringBetween(granted.getAuthority(),
        // AuthConstant.ROLE_PREFIX, AuthConstant.ROLE_SUFFIX);
        //            roleIds.add(Long.parseLong(id));
        //        });
        //        return roleIds;
        return new ArrayList<>();
    }

    /**
     * get role names
     */
    public static List<String> getRoleNames() {
        //        List<String> roleNames = new ArrayList<>();
        //        Authentication authentication = getAuthentication();
        //        if (authentication == null) {
        //            return roleNames;
        //        }
        //        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //        authorities.stream().filter(granted -> StringUtils.startsWith(granted.getAuthority(),
        // AuthConstant.ROLE_PREFIX)).forEach(granted -> {
        //            String name = StringUtils.substringAfter(granted.getAuthority(),
        // AuthConstant.ROLE_SUFFIX);
        //            roleNames.add(name);
        //        });
        //        return roleNames;
        return new ArrayList<>();
    }

    /**
     * password encode
     */
    public static String encode(String salt, String password) {
        return SaSecureUtil.aesEncrypt(salt, password);
    }

    /**
     * password decrypt
     */
    public static String decrypt(String salt, String password) {
        return SaSecureUtil.aesDecrypt(salt, password);
    }
}
