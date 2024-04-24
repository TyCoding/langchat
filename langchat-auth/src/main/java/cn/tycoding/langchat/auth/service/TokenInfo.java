package cn.tycoding.langchat.auth.service;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@Data
@Accessors(chain = true)
public class TokenInfo<T> {

    /**
     * Token
     */
    private String token;

    /**
     * 过期时间
     */
    private Long expiration;

    /**
     * 用户信息
     */
    private T user;
}
