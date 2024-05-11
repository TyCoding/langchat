package cn.tycoding.langchat.common.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author tycoding
 * @since 2024/1/2
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code = HttpStatus.OK.value();

    private String message = HttpStatus.OK.getReasonPhrase();

    private T result;

    public R() {
        super();
    }

    public R(int code, String message) {
        this.code = code;
        this.message = message;
    }

    protected R(T result) {
        this.result = result;
    }

    protected R(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    protected R(T result, HttpStatus httpStatus) {
        this.result = result;
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

    protected R(Throwable e) {
        super();
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.message = e.getMessage();
    }

    public static <T> R<T> ok(T result) {
        return new R(result);
    }

    public static <T> R<T> ok(T result, HttpStatus httpStatus) {
        return new R(result, httpStatus);
    }

    public static <T> R<T> ok() {
        return new R<>();
    }

    public static <T> R<T> ok(String message) {
        return new R<>(HttpStatus.OK.value(), message);
    }

    public static <T> R<T> ok(HttpStatus httpStatus) {
        return new R<>(httpStatus);
    }

    public static <T> R<T> fail(String message) {
        return new R<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public static <T> R<T> fail(int code, String message) {
        return new R<>(code, message);
    }

    public static <T> R<T> fail(HttpStatus httpStatus) {
        return new R<>(httpStatus);
    }

    public static <T> R<T> fail(Throwable e) {
        return new R<>(e);
    }
}
