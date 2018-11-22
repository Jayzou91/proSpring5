package com.fish.spring5.dto;

import java.util.List;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/8
 */
public class Page<T> {

    private List<T> rows;
    private long total;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
