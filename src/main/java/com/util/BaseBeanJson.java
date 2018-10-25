package com.util;

/**
 * 返回json
 *
 * @Author Tyler Yin
 */
public class BaseBeanJson {

    /**
     * 请求返回码
     */
    protected int code = 0;
    /**
     * 请求返回描述
     */
    protected String msg;
    /**
     * 返回的实体对象
     */
    protected Object data;

    public BaseBeanJson(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseBeanJson(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
