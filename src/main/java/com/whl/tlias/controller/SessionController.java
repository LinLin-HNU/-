package com.whl.tlias.controller;




import com.whl.tlias.pojo.Result;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HttpSession演示
 */

@RestController
public class SessionController {
    //设置Cookie
    @GetMapping("/c1")
    public Result cookie1(HttpServletResponse response){
        response.addCookie(new Cookie("login_username","wanghaolin"));
        return Result.success();
    }

    //获取Cookie
    @GetMapping("/c2")
    public Result cookie2(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login_username")){
                System.out.println("login_username: "+cookie.getValue());
            }
        }
        return Result.success();
    }

    //设置Session
    @GetMapping("/s1")
    public Result session1(HttpSession session){
        System.out.println("HttpSession-s1: "+session.hashCode());
        session.setAttribute("login_user","wanghaoyu");
        return Result.success();
    }

    //获取Session
    @GetMapping("/s2")
    public Result session2(HttpSession session){
        System.out.println("HttpSession-s2："+session.hashCode());
        Object loginUser = session.getAttribute("login_user");
        System.out.println("login_user: "+loginUser);
        return Result.success();
    }
}
