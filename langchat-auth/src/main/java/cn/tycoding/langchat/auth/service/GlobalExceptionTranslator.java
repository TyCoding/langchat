package cn.tycoding.langchat.auth.service;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.tycoding.langchat.common.exception.AuthException;
import cn.tycoding.langchat.common.exception.ServiceException;
import cn.tycoding.langchat.common.utils.R;
import java.nio.file.AccessDeniedException;
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
 * 全局异常拦截（注意：这种方式只能拦截经过Controller的异常，未经过Controller的异常拦截不到）
 *
 * @author tycoding
 * @since 2024/1/5
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

    @ExceptionHandler({AuthException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(AuthException e) {
        e.printStackTrace();
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R handleError(AccessDeniedException e) {
        e.printStackTrace();
        return R.fail(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({RedisConnectionFailureException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(RedisConnectionFailureException e) {
        e.printStackTrace();
        return R.fail("The Redis connection is abnormal");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(Exception e) {
        e.printStackTrace();
        return R.fail(e);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleError(MethodArgumentTypeMismatchException e) {
        e.printStackTrace();
        return R.fail(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public R handleError(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return R.fail(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleError(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return R.fail(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SaTokenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(SaTokenException e) {
        e.printStackTrace();
        return R.fail(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({NotLoginException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(NotLoginException e) {
        e.printStackTrace();
        return R.fail(HttpStatus.UNAUTHORIZED);
    }

}
