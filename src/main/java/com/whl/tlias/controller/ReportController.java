package com.whl.tlias.controller;

import com.whl.tlias.pojo.EmpGenderData;
import com.whl.tlias.pojo.EmpJobData;
import com.whl.tlias.pojo.Result;
import com.whl.tlias.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/5 17:10
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getReport() {
        EmpJobData empJobData=reportService.getReport();
        return Result.success(empJobData);
    }


    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {

            List<EmpGenderData> gender = reportService.getGender();
        return Result.success(gender);
    }
}
