package com.whl.tlias.service;

import com.whl.tlias.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/25 21:57
 */

public interface DeptService {

    List<Dept> findAll();

    void deleteById(Integer id);

    void addDept(Dept dept);

    void updateDept(Dept dept);

    Dept findById(Integer id);
}
