package com.whl.tlias.service.impl;

import com.whl.tlias.mapper.DeptMapper;
import com.whl.tlias.pojo.Dept;
import com.whl.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/25 21:57
 */
@Service
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    public DeptServiceImpl(DeptMapper deptMapper) {
        this.deptMapper = deptMapper;
    }

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }


    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }

    /*
     * 新增部门
     * 在这里注意，要实现新增creatTime和updateTime，需要使用LocalDateTime.now()方法获取当前时间
     * LocalDateTime 是Java 8中引入的一个类，位于 java.time 包中。它表示不带时区的日期和时间，包括年、月、日、时、分和秒。LocalDateTime 类提供了许多方法来操作日期和时间，例如获取当前日期和时间、格式化日期和时间、加减日期时间等
     * LocalDateTime.now() 是 LocalDateTime 类的一个静态方法。这个方法返回当前的日期和时间，不带时区信息。具体来说：

    LocalDateTime.now() 调用时会获取系统默认时区的当前日期和时间。
    它返回一个 LocalDateTime 对象，表示当前的日期和时间
     */
    @Override
    public void addDept(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.addDept(dept);
    }

    @Override
    public void updateDept( Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }

    @Override
    public Dept findById(Integer id) {
        return deptMapper.findById(id);
    }
}
