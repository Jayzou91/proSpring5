package com.fish.spring5.common;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/8
 */
public class ResultBean<T> {

    public static final int SUCCESS = 0;
    public static final String SUCCESS_MSG = "success";

    private T data;
    private int code;
    private String msg;

    public ResultBean() {
    }

    public ResultBean(T data, int code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
