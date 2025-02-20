package com.whl.tlias.pojo;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/2/5 17:37
 */
public class EmpGenderData {
    String name;
    Integer value;

    public EmpGenderData(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Integer getvalue() {
        return value;
    }
    public void setvalue(Integer value) {
        this.value = value;
    }
}
