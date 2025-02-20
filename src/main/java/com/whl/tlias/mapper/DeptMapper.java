package com.whl.tlias.mapper;

import com.whl.tlias.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/25 21:56
 */
@Mapper
public interface DeptMapper {

    /*
     * 查询所有部门信息
     */
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

    @Delete(("delete from dept where id = #{id}"))
    void deleteById(Integer id);

    @Insert("INSERT INTO dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void addDept(Dept dept);

    @Update("UPDATE dept SET name = #{name}, update_time = #{updateTime} WHERE id = #{id}")
    void updateDept(Dept dept);

    @Select("SELECT id, name, create_time, update_time FROM dept WHERE id = #{id}")
    Dept findById(Integer id);
}
