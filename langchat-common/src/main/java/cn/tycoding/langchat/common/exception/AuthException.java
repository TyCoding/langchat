package cn.tycoding.langchat.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author tycoding
 * @since 2024/1/2
 */
@Getter
public class AuthException extends RuntimeException {

    private static final long serialVersionUID = -1068765335343416833L;

    private final int code;

    public AuthException() {
        super(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        this.code = HttpStatus.UNAUTHORIZED.value();
    }

    public AuthException(String message) {
        super(message);
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public AuthException(int code, String message) {
        super(message);
        this.code = code;
    }
}
