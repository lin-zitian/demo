package com.example.demo.config;

import com.example.demo.repository.model.Device;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Device> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Device> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 使用 Jackson2JsonRedisSerializer 来序列化和反序列化 Device 对象
        Jackson2JsonRedisSerializer<Device> serializer = new Jackson2JsonRedisSerializer<>(Device.class);

        // 设置 key 的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        // 设置 value 的序列化器
        template.setValueSerializer(serializer);
        // 设置 hash key 的序列化器
        template.setHashKeySerializer(new StringRedisSerializer());
        // 设置 hash value 的序列化器
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}