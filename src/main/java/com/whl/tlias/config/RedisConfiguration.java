package com.whl.tlias.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.whl.tlias.pojo.Emp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/17 17:37
 * Redis配置类
 */
@Configuration
public class RedisConfiguration {

    @Bean
//    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//        System.out.println("开始配置RedisTemplate");
//        RedisTemplate redisTemplate = new RedisTemplate();
//        // 设置redis连接工厂
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        //设置redis key的序列化器
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }

    public RedisTemplate<String, Emp> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Emp> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 设置key的序列化方式
        template.setKeySerializer(new StringRedisSerializer());

        // 设置value的序列化方式
        Jackson2JsonRedisSerializer<Emp> serializer = new Jackson2JsonRedisSerializer<>(Emp.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        serializer.setObjectMapper(objectMapper);
        template.setValueSerializer(serializer);

        return template;
    }
}
