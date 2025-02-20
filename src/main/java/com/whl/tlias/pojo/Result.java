package com.whl.tlias.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/25 21:50
 * 统一响应结果实体类
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Result {
    private Integer code; // 响应码
    private String message; // 响应信息
    private Object data; // 响应数据

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result() {

    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

    /*
     * 成功响应
     */
    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.message = "success";
        return result;
    }

    /*
     * 成功响应（带数据）
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.code = 1;
        result.message = "success";
        result.data = data;
        return result;
    }

    /*f
     * 失败响应
     */
    public static Result error(String message) {
        Result result = new Result();
        result.code = 0;
        result.message = message;
        return result;
    }


}
