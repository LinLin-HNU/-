package com.whl.tlias.config;

import com.whl.tlias.Interceptor.DemoInterceptor;
import com.whl.tlias.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/6 17:08
 */
@Configuration //这个注解包含了@Component注解，因此可以被Spring容器管理
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    private DemoInterceptor demoInterceptor;

//    @Autowired
//    private LoginInterceptor loginInterceptor;

    //注册拦截器

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(demoInterceptor).addPathPatterns("/**");
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
//    }
}
