package com.gosuncn.efs.bean;



import com.gosuncn.efs.common.ResultCode;

import java.io.Serializable;

/**
 * Copyright © 1997 - 2018 Gosuncn. All Rights Reserved.
 *
 * @author Michael
 * @date 10/24/2018
 * @email 2751358839@qq.com
 * @description 接口统一回调结果
 */
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -1L;
    private int code = -1;
    private String message = "";
    private T data;
    private long serial=System.currentTimeMillis();

    public ApiResult() {
        serial=System.currentTimeMillis();
    }


    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.serial=System.currentTimeMillis();
    }

    /**
     * 使用编码默认信息
     *
     * @param resultCode 错误编码
     * @param data       数据
     */
    public ApiResult(ResultCode resultCode, T data) {
        super();
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
        this.serial = System.currentTimeMillis();
    }

    /**
     * 自定义编码信息
     *
     * @param resultCode
     * @param message
     * @param data
     */
    public ApiResult(ResultCode resultCode, String message, T data) {
        super();
        this.code = resultCode.getCode();
        this.message = message;
        this.data = data;
        this.serial = System.currentTimeMillis();
    }

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
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getSerial() {
        return serial;
    }

    public void setSerial(long serial) {
        this.serial = serial;
    }
}
