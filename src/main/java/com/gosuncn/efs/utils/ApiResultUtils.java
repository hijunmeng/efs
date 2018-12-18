package com.gosuncn.efs.utils;


import com.gosuncn.efs.bean.ApiResult;
import com.gosuncn.efs.common.ResultCode;

/**
 * @author Michael
 * @description 返回Result 的工具类
 */
public class ApiResultUtils {
    public static <T> ApiResult<T> success(String message, T data) {
        ApiResult<T> result = new ApiResult(ResultCode.SUCCESS.getCode(), message, data);
        return result;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> result = new ApiResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
        return result;
    }

    public static <T> ApiResult<T> success(String message) {
        ApiResult<T> result = new ApiResult(ResultCode.SUCCESS.getCode(), message, "");
        return result;
    }

    public static <T> ApiResult<T> success() {
        ApiResult<T> result = new ApiResult(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), "");
        return result;
    }

    public static <T> ApiResult<T> failed() {
        ApiResult<T> result = new ApiResult(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), "");
        return result;
    }

    public static <T> ApiResult<T> failed(String message) {
        ApiResult<T> result = new ApiResult(ResultCode.FAILED.getCode(), message, "");
        return result;
    }

    public static <T> ApiResult<T> failed(String message, T t) {
        ApiResult<T> result = new ApiResult(ResultCode.FAILED.getCode(), message, t);
        return result;
    }


    public static <T> ApiResult<T> error(int errorCode, String message) {
        ApiResult<T> result = new ApiResult(errorCode, message, "");
        return result;
    }

    public static <T> ApiResult<T> error(int errorCode, String message, T t) {
        ApiResult<T> result = new ApiResult(errorCode, message, t);
        return result;
    }

    public static <T> ApiResult<T> error(ResultCode resultCode) {
        ApiResult<T> result = new ApiResult(resultCode.getCode(), resultCode.getMessage(), "");
        return result;
    }


}
