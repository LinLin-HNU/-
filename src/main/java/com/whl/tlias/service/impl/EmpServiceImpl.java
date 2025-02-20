package com.whl.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.whl.tlias.mapper.EmpExprMapper;
import com.whl.tlias.mapper.EmpMapper;
import com.whl.tlias.pojo.*;
import com.whl.tlias.service.EmpService;
import com.whl.tlias.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/27 15:44
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    //复杂模式
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize,String name , Integer gender,
//                                 LocalDate begin ,
//                                LocalDate end) {
     //简化模式
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {


        //--------------------------常规查询操作----------------------------------

//        /*
//         * 调用EmpMapper的方法，返回总记录数
//         */
//        Long total = empMapper.selectCount();
//
//        /*
//         * 调用EmpMapper的方法，返回分页数据
//         */
//        List<Emp> rows = empMapper.selectAll((page - 1) * pageSize, pageSize);
//
//        /*
//         * 封装PageResult对象并返回
//         */
//        return new PageResult<Emp>(total, rows);








        //--------------------------PageHelper操作----------------------------------
        /*
         * 1.调用PageHelper的startPage方法，传入当前页码和每页记录数
         */
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        /*
         * 2.调用EmpMapper的方法，返回分页数据
         */
        List<Emp> rows = empMapper.selectAll(empQueryParam);

        /*
         * Page是一个分页对象，继承于ArrayList，ArrayList又实现了List接口
         * 可以使用强转将List对象转换为Page对象
         * 然后可以调用Page对象的getTotal方法，返回总记录数
         * 调用Page对象的getResult方法，返回分页数据
         */
        Page p = (Page<Emp>) rows;
        long total = p.getTotal();
        List<Emp> result = p.getResult();

        /*
         * 3.封装PageResult对象并返回
         */
        return new PageResult<Emp>(total, result);

    }

    @Override
    @Transactional
    public void save(Emp emp) {
        //1.保存员工的基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        //2.保存员工的工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合，为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})//任何类型的异常，都需要回滚
    public void delete(List<Integer> ids) {
        //1.删除员工的基本信息
        empMapper.deleteBatch(ids);
        //2.删除员工的所有工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getEmpById(Integer id) {
        //查询员工的信息
        Emp emp = empMapper.selectById(id);
//        emp.setUpdateTime(LocalDateTime.now());
        return emp;
    }

    /*
     * 更新员工基本信息时，分为两种情况：
     * 1.更新员工的全部信息
     *  这样，只需把传来的json字符串反序列化为Emp对象，然后调用更新的sql语句即可
     * 2.更新员工的部分信息
     *  这种情况，需要使用动态sql语句，查看传来的json字符串中哪些不为空，然后更新相应的字段
     *
     * 而更新员工的工作经历信息，可以使用先删后加的策略，先删除原有的数据，然后再插入新的数据
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(Emp emp) {
        //1.更新员工的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
        //2.更新员工的工作经历信息
        //先删除原有的数据
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));//emp.getId()返回的是int类型，但是deleByEmpIds方法需要传入List<Integer>类型，所以需要转换
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //插入新的数据
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
            }
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.根据用户名和密码查询员工信息
        Emp loginEmp = empMapper.selectByUsernameAndPassword(emp);
        //生成JWT token
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("id", loginEmp.getId());
        claims.put("username", loginEmp.getUsername());
        String jwt = JwtUtils.generateJwt(claims);
        //3.如果查询到，封装LoginInfo对象并返回

        if(loginEmp != null){
            return new LoginInfo(loginEmp.getId(), loginEmp.getUsername(), loginEmp.getPassword(),jwt);
        }

        //2.如果查询不到，返回null
        return null;
    }
}
