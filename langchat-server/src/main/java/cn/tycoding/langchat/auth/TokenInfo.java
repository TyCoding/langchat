package cn.tycoding.langchat.auth;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tycoding
 * @since 2024/4/22
 */
@Data
@Accessors(chain = true)
public class TokenInfo implements Serializable {
    private static final long serialVersionUID = 1957572130312305812L;

    private String token;
    private Long expiration;
}
