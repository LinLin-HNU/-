package com.whl.tlias.service.impl;

import com.whl.tlias.mapper.EmpMapper;
import com.whl.tlias.pojo.EmpGenderData;
import com.whl.tlias.pojo.EmpJobData;
import com.whl.tlias.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/5 17:10
 */
@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<EmpGenderData> getGender() {
        //1.调用Mapper接口，获取统计数据
        List<EmpGenderData> genderData = empMapper.countByGender();
        //2.返回结果
        return genderData;
    }

    @Override
    public EmpJobData getReport() {
        //1.调用Mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countByJob();
        //2.组装结果，并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("data")).toList();
        return new EmpJobData(jobList, dataList);
    }


}
