package com.whl.tlias.mapper;

import com.whl.tlias.pojo.Emp;
import com.whl.tlias.pojo.EmpGenderData;
import com.whl.tlias.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/27 15:43
 */
@Mapper
public interface EmpMapper {

    // ---------------------普通查询-------------------------------------------
//    /*
//     * 查询所有员工信息-分页
//     * 注意，这里的dept.name要用别名，否则会与emp表中的name字段冲突，且参数字段是dept_name，如果这里用name，那么字段会匹配不上
//     */
//    @Select("select emp.*,dept.name dept_name from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc limit #{start},#{pageSize};")
//    public List<Emp> selectAll(Integer start, Integer pageSize);
//
//    /*
//    * 查询总记录数
//     */
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id;")
//    public Long selectCount();


    // ---------------------PageHelper插件的使用-----------------------------------
    /*
     * 注意条件：
     * 1. sql语句后不要加分号（使用常规查询加不加都行）
     * 2. 在EmpServiceImpl类中，使用PageHelper.startPage(start, pageSize);后，只对其后的第一条sql语句的分页和count(*)生效，
     */
//    @Select("select emp.*,dept.name dept_name from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc")
    //@Select()和xml方式二选一


//    public List<Emp> selectAll(String name, Integer gender, LocalDate begin, LocalDate end);

    public List<Emp> selectAll(EmpQueryParam empQueryParam);

    /*
     * 新增员工基本信息
     * @Options()注解用于设置主键返回
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)\n" +
            "value(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteBatch(List<Integer> ids);


    /*
     * 根据id查询员工信息
     * 这里的xml文件中不能用resultType，因为工作经历可能不止一段，如果用resultType，那么可能回返回多个Emp对象，但规定的返回值是一个Emp对象，
     * 所以用resultMap来重新映射结果集，即一个基本信息和多个工作经历的Emp对象
     */
    Emp selectById(Integer id);

    void update(Emp emp);

    /*
     * 报表统计
     * 员工职位人数统计
     * 这里报错是缺少@MapKey注解，但其实是误报，想处理这个报错可以：1.卸载MybatisX插件 2.加上注解
     */
    public List<Map<String,Object>> countByJob();

    /*
     * 员工性别统计
     */
    public List<EmpGenderData> countByGender();

    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
