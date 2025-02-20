package com.whl.tlias.mapper;

import com.whl.tlias.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/16 18:22
 * SpringAop Mapper层
 * 用于往AOP的日志表中插入数据
 * id
 * 操作员id
 * 操作时间
 * 类名
 * 方法名
 * 方法参数
 * 返回值
 * 耗时
 */




@Mapper
public interface OperateLogMapper {

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);

}
