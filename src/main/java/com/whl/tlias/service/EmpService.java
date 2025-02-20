package com.whl.tlias.service;

import com.whl.tlias.pojo.Emp;
import com.whl.tlias.pojo.EmpQueryParam;
import com.whl.tlias.pojo.LoginInfo;
import com.whl.tlias.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/27 15:44
 */
public interface EmpService {
    //复杂模式
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
//                          LocalDate begin ,
//                          LocalDate end);

    //简化模式
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getEmpById(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
