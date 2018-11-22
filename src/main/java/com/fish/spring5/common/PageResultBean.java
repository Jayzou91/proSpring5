package com.fish.spring5.common;

import com.fish.spring5.dto.Page;

import java.util.List;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/10
 */
public class PageResultBean<T> {
    private List<T> rows;
    private long total;
    private int code;
    private String msg;

    public PageResultBean() {
    }

    public PageResultBean(List<T> rows, long totals, int code, String msg) {
        this.rows = rows;
        this.total = totals;
        this.code = code;
        this.msg = msg;
    }

    public PageResultBean(Page<T> page, int code, String msg) {
        if (page != null) {
            this.rows = page.getRows();
            this.total = page.getTotal();
        } else {
            this.rows = null;
            this.total = 0L;
        }
        this.code = code;
        this.msg = msg;
    }

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
