package com.whl.tlias.filter;

import com.whl.tlias.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/6 10:25
 */
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //强转类型，把请求和响应转换成HttpServletRequest和HttpServletResponse
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取请求路径
        String requestURI = request.getRequestURI();//相比于url，没有获取http前缀，获取的是8080后面的路径

        //2.判断是否登录
        if(requestURI.contains("/login")){
            //登录页面，放行
            filterChain.doFilter(request, response);
            return; //如果是登录页面，那就放行，让它去访问登录的接口，直接返回，没必要去执行后面的内容了
        }

        //3.获取请求头token
        String token = request.getHeader("token");

        //4.执行filterChain.doFilter()方法，判断token是否为空，为空则返回401
        if(token == null || token.isEmpty()){
            response.setStatus(401);
            return;
        }
        //5.判断token是否有效，有效则放行，否则返回401
        try{
            JwtUtils.parseJWT(token);
        }catch (Exception e){
            response.setStatus(401);
            return;
        }


        //6.执行filterChain.doFilter()方法，放行
        filterChain.doFilter(request, response);
    }
}
