package com.bs.ssh.beans;

/**
 * HTTP响应体
 *
 * @author Egan
 * @date 2018/11/11 14:18
 **/
public class ResponseBody<T> {
    private int code;
    private String message;
    private T Data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }
}
