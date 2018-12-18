package com.gosuncn.efs.common;

import com.gosuncn.efs.bean.ApiResult;
import com.gosuncn.efs.utils.ApiResultUtils;
import com.gosuncn.efs.utils.Log;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * 异常处理器，捕获所有异常，并按照统一格式返回
 * Created by hwj on 2017/5/21.
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult<String> handle(HttpServletRequest request, Exception e )  {
        if(e instanceof HttpRequestMethodNotSupportedException){
            return ApiResultUtils.error(-1,"请求方式(Get/Post)错误","HttpRequestMethodNotSupportedException");
        }else if(e instanceof MissingServletRequestParameterException){
            return ApiResultUtils.error(-1,"缺少参数","MissingServletRequestParameterException");
        }else if(e instanceof MethodArgumentTypeMismatchException){
            return ApiResultUtils.error(-1,"参数类型错误","MethodArgumentTypeMismatchException");
        } else if (e instanceof MethodArgumentNotValidException) {//使用@Valid产生的异常
            return ApiResultUtils.failed(((MethodArgumentNotValidException)e).getBindingResult().getAllErrors().get(0).getDefaultMessage());//请求参数验证不通过异常
            //return ApiResultUtils.failed(e.getMessage());//请求参数验证不通过异常
        }else if (e instanceof ConstraintViolationException) {//使用@Validated产生的异常
            return ApiResultUtils.failed(e.getMessage());//请求参数验证不通过异常
        }else if(e instanceof HttpMediaTypeNotSupportedException){
            return ApiResultUtils.failed("请求Content-Type错误");
        }else if(e instanceof BusinessException){
            return ApiResultUtils.error(((BusinessException)e).getCode(), e.getMessage());
        }

        e.printStackTrace();
        Log.error("系统异常:" + e.getClass());
        return ApiResultUtils.error(-1, "未知错误", e.getClass().toString());
    }
}
