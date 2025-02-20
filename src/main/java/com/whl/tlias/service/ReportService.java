package com.whl.tlias.service;

import com.whl.tlias.pojo.EmpGenderData;
import com.whl.tlias.pojo.EmpJobData;

import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/5 17:10
 */
public interface ReportService {
    List<EmpGenderData> getGender();

    EmpJobData getReport();
}
