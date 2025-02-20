package com.whl.tlias.Interceptor;

import com.whl.tlias.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/6 17:27
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //1.获取请求路径
        //在配置类config下的WebConfig中，如果有excludePathPatterns("/login"),就不会拦截/login请求，则这里的前两步就不用做了
        String requestURI = request.getRequestURI();//相比于url，没有获取http前缀，获取的是8080后面的路径

        //2.判断是否登录
        if(requestURI.contains("/login")){
            //登录页面，放行
//            filterChain.doFilter(request, response);
            return true;  //如果是登录页面，那就放行，让它去访问登录的接口，直接返回，没必要去执行后面的内容了
        }

        //3.获取请求头token
        String token = request.getHeader("token");

        //4.执行filterChain.doFilter()方法，判断token是否为空，为空则返回401
        if(token == null || token.isEmpty()){
            response.setStatus(401);
            return false;
        }
        //5.判断token是否有效，有效则放行，否则返回401
        try{
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }


        //6.执行filterChain.doFilter()方法，放行
        return true;
    }

}
