package com.whl.tlias.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/18 16:05
 */
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // 注册JavaTimeModule模块以支持Java 8日期时间类型
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}