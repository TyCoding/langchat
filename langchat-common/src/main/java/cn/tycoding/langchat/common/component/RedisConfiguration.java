package cn.tycoding.langchat.common.component;

import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @author tycoding
 * @since 2024/1/2
 */
@Component
@EnableCaching
@Configuration
@AllArgsConstructor
public class RedisConfiguration {

    private final RedisConnectionFactory connectionFactory;

    @Bean(name = {"redisTemplate"})
    public RedisTemplate<String, Object> redisTemplate() {
        // 指定相应的序列化方案
        StringRedisSerializer keySerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer valueSerializer = new GenericJackson2JsonRedisSerializer();

        // 构建RedisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setHashKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setHashValueSerializer(valueSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean(name = {"stringRedisTemplate"})
    public StringRedisTemplate stringRedisTemplate() {
        // 构建StringRedisTemplate
        StringRedisTemplate stringTemplate = new StringRedisTemplate();
        stringTemplate.setConnectionFactory(connectionFactory);
        stringTemplate.afterPropertiesSet();
        return stringTemplate;
    }
}
