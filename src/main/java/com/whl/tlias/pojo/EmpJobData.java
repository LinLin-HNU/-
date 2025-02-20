package com.whl.tlias.pojo;

import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/5 16:57
 */
public class EmpJobData {
    List<Object> jobList;
    List<Object> dataList;

    public EmpJobData(List<Object> jobList, List<Object> dataList) {
        this.jobList = jobList;
        this.dataList = dataList;
    }

    public List<Object> getJobList() {
        return jobList;
    }

    public void setJobList(List<Object> jobList) {
        this.jobList = jobList;
    }

    public List<Object> getDataList() {
        return dataList;
    }

    public void setDataList(List<Object> dataList) {
        this.dataList = dataList;
    }
}
