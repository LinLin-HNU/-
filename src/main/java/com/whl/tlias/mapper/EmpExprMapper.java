package com.whl.tlias.mapper;

import com.whl.tlias.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/30 16:52
 */
@Mapper
public interface EmpExprMapper {
    /*
     * 批量保存员工工作经历信息
     */
    void insertBatch(List<EmpExpr> exprList);

    void deleteByEmpIds(List<Integer> ids);

}
