package com.whl.tlias.controller;

import com.whl.tlias.pojo.Emp;
import com.whl.tlias.pojo.LoginInfo;
import com.whl.tlias.pojo.Result;
import com.whl.tlias.service.EmpService;
import com.whl.tlias.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/5 19:11
 */
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo != null){
            return Result.success(loginInfo);
        }
        return Result.error("用户名或密码错误");
    }
}
