package cn.tycoding.langchat.server.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tycoding
 * @since 2023/12/20
 */
@Data
@Accessors(chain = true)
public class TokenInfo<T> implements Serializable {

    private static final long serialVersionUID = 6422723675878957517L;

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
