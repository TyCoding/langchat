package cn.tycoding.langchat.common.constant;

/**
 * @author tycoding
 * @since 2024/1/15
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
     * 用户信息缓存
     */
    String USER_DETAIL_KEY = REDIS_KEY_PREFIX + "user_details";

}
