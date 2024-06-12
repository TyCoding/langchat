package cn.tycoding.langchat.auth.service;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.tycoding.langchat.common.exception.AuthException;
import cn.tycoding.langchat.common.exception.ServiceException;
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

import java.nio.file.AccessDeniedException;

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
        log.error("----------业务异常----------");
        e.printStackTrace();
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({AuthException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(AuthException e) {
        log.error("----------授权错误----------");
        e.printStackTrace();
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public R handleError(AccessDeniedException e) {
        log.error("----------没有访问权限----------");
        e.printStackTrace();
        return R.fail(HttpStatus.FORBIDDEN.value(), "没有访问权限");
    }

    @ExceptionHandler({RedisConnectionFailureException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(RedisConnectionFailureException e) {
        log.error("----------Redis连接异常----------");
        e.printStackTrace();
        return R.fail("Redis连接异常");
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public R handleError(Exception e) {
        log.error("----------服务器异常----------");
        e.printStackTrace();
        return R.fail(e);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleError(MethodArgumentTypeMismatchException e) {
        log.error("----------参数类型不匹配异常----------");
        e.printStackTrace();
        return R.fail(HttpStatus.BAD_REQUEST.value(), "参数类型不匹配异常");
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public R handleError(HttpRequestMethodNotSupportedException e) {
        log.error("----------请求方法不支持----------");
        e.printStackTrace();
        return R.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), "请求方法不支持");
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R handleError(HttpMessageNotReadableException e) {
        log.error("----------缺少请求参数----------");
        e.printStackTrace();
        return R.fail(HttpStatus.BAD_REQUEST.value(), "缺少请求参数");
    }

    @ExceptionHandler({SaTokenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(SaTokenException e) {
        log.error("----------身份认证异常----------");
        e.printStackTrace();
        return R.fail(HttpStatus.UNAUTHORIZED.value(), "身份认证失败");
    }

    @ExceptionHandler({NotLoginException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R handleError(NotLoginException e) {
        log.error("----------身份认证异常----------");
        e.printStackTrace();
        return R.fail(HttpStatus.UNAUTHORIZED.value(), "身份认证失败");
    }

}
