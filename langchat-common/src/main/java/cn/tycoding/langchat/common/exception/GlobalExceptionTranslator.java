package cn.tycoding.langchat.common.exception;

import cn.tycoding.langchat.common.utils.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author tycoding
 * @since 2024/1/2
 */
@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionTranslator {

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(ServiceException e) {
        e.printStackTrace();
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({RedisConnectionFailureException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(RedisConnectionFailureException e) {
        e.printStackTrace();
        return R.fail("Redis connection failed");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public R handleError(HttpRequestMethodNotSupportedException e) {
        return R.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), "Not support request method");
    }

    @ExceptionHandler({HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleError(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return R.fail(HttpStatus.BAD_REQUEST.value(), "Missing request parameter");
    }

    @ExceptionHandler({Exception.class, Exception.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public R handleError(Exception e) {
        System.out.println("-------");
        e.printStackTrace();
        return R.fail(HttpStatus.SERVICE_UNAVAILABLE.value(), "Server Exception");
    }
}
