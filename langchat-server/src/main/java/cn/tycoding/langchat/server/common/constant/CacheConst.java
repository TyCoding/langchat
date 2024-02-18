package cn.tycoding.langchat.server.common.constant;

/**
 * @author tycoding
 * @since 2023/11/15
 */
public interface CacheConst {

    /**
     * 系统所有Redis缓存Key前缀 prefix
     */
    String REDIS_KEY_PREFIX = "langchat:";

    /**
     * Auth缓存前缀
     */
    String AUTH_PREFIX = REDIS_KEY_PREFIX + "auth:";

    /**
     * Auth Session缓存前缀
     */
    String AUTH_SESSION_PREFIX = REDIS_KEY_PREFIX + "auth:session:";

    /**
     * Auth Session缓存变量前缀
     */
    String AUTH_USER_INFO_KEY = "USER_INFO";

    String AUTH_TOKEN_INFO_KEY = "TOKEN_INFO";

    /**
     * 验证码缓存前缀
     */
    String CAPTCHA_PREFIX = REDIS_KEY_PREFIX + "auth:captcha:";

    /**
     * 用户信息缓存
     */
    String USER_DETAIL_KEY = REDIS_KEY_PREFIX + "user_details";

    /**
     * 菜单权限缓存
     */
    String MENU_DETAIL_KEY = REDIS_KEY_PREFIX + "menu_details";
}
