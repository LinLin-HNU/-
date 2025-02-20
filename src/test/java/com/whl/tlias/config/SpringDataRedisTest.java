package com.whl.tlias.config;

import io.lettuce.core.ScriptOutputType;
import org.aspectj.lang.annotation.Around;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/17 17:42
 */
@SpringBootTest
public class SpringDataRedisTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplate(){
        System.out.println(redisTemplate);
        ValueOperations valueOperations = redisTemplate.opsForValue();
    }

    @Test
    public void testString(){
        //set get setex setnx
        redisTemplate.opsForValue().set("城市", "北京");
        String city = (String)redisTemplate.opsForValue().get("城市");
        System.out.println(city);
        redisTemplate.opsForValue().set("城市", "上海", 10, TimeUnit.SECONDS);
        redisTemplate.opsForValue().setIfAbsent("学校", "清华大学");
        redisTemplate.opsForValue().setIfAbsent("学校", "北京大学");
    }

    @Test
    public void testHash(){
        //hset hget hdel hkeys hvals
        HashOperations hashOperations = redisTemplate.opsForHash();

        hashOperations.put("100","name","tom");
        hashOperations.put("100","age","20");

        String name = (String)hashOperations.get("100", "name");
        System.out.println(name);

        Set keys = hashOperations.keys("100");//获取hash的所有key
        System.out.println(keys);

        List values = hashOperations.values("100");
        System.out.println(values);

        hashOperations.delete("100", "age");
    }
}
