package com.whl.tlias.controller;

import com.whl.tlias.pojo.Emp;
import com.whl.tlias.pojo.EmpQueryParam;
import com.whl.tlias.pojo.PageResult;
import com.whl.tlias.pojo.Result;
import com.whl.tlias.service.EmpService;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/27 15:43
 */
//@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private EmpService empService;

    @Autowired
    private RedisTemplate redisTemplate;

    /*
     * 分页查询
     */
    //如果controller层的方法参数较多，且未来可能会继续增加，这会使方法签名变得复杂难以维护，此时可以考虑将多个请求参数封装为一个对象
    //复杂模式
//    @GetMapping
//    public Result page(@RequestParam(value = "page", defaultValue = "1") Integer page,
//                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
//                       String name , Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin ,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
////        log.info("page:{},pageSize:{}", page, pageSize);
//        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
//        return Result.success(pageResult);
//    }

    /*
     * 分页查询
     */
    //简化模式-把参数封装为一个对象
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
//        log.info("page:{},pageSize:{}", page, pageSize);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /*
     * 新增员工
     */
    @PostMapping
//    @CachePut(cacheNames = "empCache",key="#emp.name") //如果使用Spring Cache缓存数据，key的生成： empCache::2
    @CachePut(cacheNames = "empCache",key="#result.data.id") //result是关键字，指代返回值，key指的是传递进来的emp形参
    //@CachePut(value = "empCache",key="#p0.id") //p0是关键字，指代第一个参数，p1指代第二个参数，key指的是传递进来的emp形参
    //Spring Cache注解：@EnableCaching @Cacheable @CachePut @CacheEvict
    public Result save(@RequestBody Emp emp){
        empService.save(emp);
        return Result.success();
    }

    /*
     * 删除员工
     */
    @DeleteMapping
//    @CacheEvict(cacheNames = "empCache",key="#ids.id")
    public Result delete(@RequestParam List<Integer> ids){
        //清理缓存数据
        for(Integer id : ids){
            String key = "emp_" + id;
            redisTemplate.delete(key);
        }

        empService.delete(ids);
        return Result.success();
    }

    /*
     * 根据id查询员工基本信息和工作经历
     */
    @GetMapping("/{id}")
    //构造redis当中的key值，根据id查询数据，如果存在，直接返回，无序查询数据库，如果不存在，查询数据库，并将数据存入Redis
    public Result getEmpById(@PathVariable("id") Integer id){
        try{
            //构造redis当中的key值
            String key = "emp_" + id;
            //查询Redis中是否存在数据
//        List<Emp> emp = (List<Emp>) redisTemplate.opsForValue().get(key);
            Emp emp = (Emp) redisTemplate.opsForValue().get(key);
            if (emp == null) {
                //如果不存在，查询数据库，
                emp = empService.getEmpById(id);
                try {
                    redisTemplate.opsForValue().set(key, emp);
                } catch (Exception e) {
                    //处理Redis设置数据时的异常，这里可以选择记录日志或者返回一个特定的结果
                    //这里我们选择记录日志但不影响获取员工信息
                    System.out.println("设置Redis缓存时出错: " + e.getMessage());
                }
            }
            //如果存在，直接返回，无序查询数据库
            return Result.success(emp);
        }catch (Exception e){
            return Result.error("无效的ID格式，请提供一个有效的整数ID");
        }
    }

    //简化模式-直接查询数据库
//    public Result getEmpById(@PathVariable("id") Integer id){
//        Emp emp = empService.getEmpById(id);
//        return Result.success(emp);
//    }

    /*
     * 更新员工信息
     */
    @PutMapping
//    @CacheEvict(cacheNames = "empCache",key="#emp.id") //如果使用Spring Cache缓存数据，key的生成： empCache::2
    public Result update(@RequestBody Emp emp){
        //清理缓存数据
        String key = "emp_" + emp.getId();
        redisTemplate.delete(key);

        empService.update(emp);
        return Result.success();
    }
}
