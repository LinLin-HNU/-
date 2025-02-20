package com.whl.tlias.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/3 17:55
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 全局异常处理方法
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        return "全局异常处理：" + e.getMessage();
    }
}
