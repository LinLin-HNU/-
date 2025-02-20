package com.whl.tlias.pojo;

import java.util.List;

/**
 * @author 王浩霖
 * @version 1.0.0 2025/1/27 15:55
 */
public class PageResult<T> {
    private Long total;//总记录数
    private List<T> rows;//当前页数据

    public PageResult() {
    }

    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
