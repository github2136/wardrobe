package com.github2136.wardrobe.model.util;

/**
 * Created by yubin on 2017/8/18.
 */

public class Response<T> {
    public static final int CODE_SUCCESSFUL = 1;
    public static final int CODE_FAILURE = 0;
    private int requestCode;
    private T data;
    private String msg;

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
