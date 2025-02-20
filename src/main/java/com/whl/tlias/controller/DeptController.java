package com.whl.tlias.controller;

import com.whl.tlias.anno.Log;
import com.whl.tlias.pojo.Dept;
import com.whl.tlias.pojo.Result;
import com.whl.tlias.service.DeptService;
//import lombok.extern.slf4j.Slf4j;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.PanelUI;
import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/25 21:58
 */
//@Slf4j
@RestController
/*
 * RestController相当于两个注解：@Controller和@ResponseBody
 * @ResponseBody注解的作用是将方法的返回值直接作为响应数据响应给前端(直接写入HTTP响应的body中)，如果返回值是数组/集合，先转JSON，再返回
 */
public class DeptController {
    /*
     日志记录器（平替：@Slf4j注解自动生成）
     */
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    /*
    部门查询
     */

    @Autowired
    private DeptService deptService;

    @RequestMapping("/depts")
    public Result queryDept(){
//        System.out.println("查询部门信息");
//        log.info("查询部门信息");
//        log.info("查询部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @DeleteMapping("/depts")
    @Log
    public Result deleteDeptById(Integer id){
        System.out.println("删除部门信息");
        deptService.deleteById(id);
        return Result.success();
    }

    /*
    部门新增
    新增的注解是@PostMapping，其作用是将HTTP请求中的数据映射到Controller的方法参数上，并将方法的返回值作为HTTP响应的body返回给前端。
     * 注意：从前端传来的数据是JSON格式的
     * 而接收的参数是对象Dept
     * 因此，需要把前端JSON格式的数据传给Dept，需要使用@RequestBody注解
     */
    @PostMapping("/depts")
    @Log
    public Result InsertDept(@RequestBody Dept dept){
        System.out.println("新增部门信息");
        deptService.addDept(dept);
        return Result.success();
    }

    /*
    部门修改-查询回显+修改数据
     */
    @GetMapping("/depts/{id}")
    public Result queryDeptById(@PathVariable("id") Integer id){
        System.out.println("查询部门信息");
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }


    @PutMapping("/depts")
    @Log
    public Result updateDept(@RequestBody Dept dept){
//        log.info("修改部门信息");
        System.out.println("修改部门信息");
        deptService.updateDept(dept);
        return Result.success();
    }
}
