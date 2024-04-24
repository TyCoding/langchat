package cn.tycoding.langchat.auth.interceptor;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author tycoding
 * @since 2024/1/5
 */
@Component
@AllArgsConstructor
public class AuthInterceptor implements WebMvcConfigurer {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CaptchaInterceptor(redisTemplate)).addPathPatterns("/auth/login");
    }
}
