package com.whl.tlias.aop;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/16 18:29
 */
import com.whl.tlias.controller.*;
import com.whl.tlias.mapper.OperateLogMapper;
import com.whl.tlias.pojo.OperateLog;
import com.whl.tlias.pojo.OperateLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class OperateLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    // 定义切入点，匹配 com.whl.tlias.controller 包下所有类的所有方法
    @Pointcut("")
    public void controllerMethods() {}

    // 环绕通知，用于记录日志
    @Around("@annotation(com.whl.tlias.anno.Log)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis(); // 方法开始时间
        Object result = joinPoint.proceed(); // 执行目标方法
        long endTime = System.currentTimeMillis(); // 方法结束时间

        // 构建日志信息
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentUserId()); // 获取当前操作人ID
        operateLog.setOperateTime(LocalDateTime.now()); // 操作时间
        operateLog.setClassName(joinPoint.getTarget().getClass().getName()); // 目标类名
        operateLog.setMethodName(joinPoint.getSignature().getName()); // 目标方法名
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs())); // 方法参数
        operateLog.setReturnValue(result.toString()); // 方法返回值
        operateLog.setCostTime(endTime - startTime); // 方法执行耗时

        // 保存日志到数据库
        operateLogMapper.insert(operateLog);

        return result;
    }

    // 获取当前操作人 ID 的方法（需要根据实际情况实现）
    private Integer getCurrentUserId() {
        // 假设从线程局部变量或上下文中获取当前用户 ID
        return 1; // 示例返回值
    }
}
