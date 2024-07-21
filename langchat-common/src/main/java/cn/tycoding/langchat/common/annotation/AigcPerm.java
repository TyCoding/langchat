package cn.tycoding.langchat.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义AIGC用户权限
 *
 * @author tycoding
 * @since 2024/4/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AigcPerm {

    String value() default "";
}
