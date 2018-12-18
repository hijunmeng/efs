package com.gosuncn.efs.common;


/**
 * Copyright © 1997 - 2018 Gosuncn. All Rights Reserved.
 *
 * @author Michael
 * @description 返回码枚举类型
 * @date 10/24/2018
 * @email 2751358839@qq.com
 */
public enum ResultCode {

    /**
     * common共用服务：20000开头
     */
    SUCCESS(0, "成功")
    , FAILED(-1, "失败")
    , UNKNOWN_ERROR(-2, "未知错误")
    , INVALID_ACCESS(-3, "非法访问")
    , NON_PARAMTER(-4, "未提供参数")
    , PARAMTER_VERIFY_ERROR(-5, "参数验证不通过")


    //业务错误码


    ;


    private int code;
    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;

    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    }
