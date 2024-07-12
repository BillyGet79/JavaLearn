package com.itheima.controller;

import com.itheima.exception.BusinessException;
import com.itheima.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {

    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException se) {
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员
        return new Result(se.getCode(), null, se.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException be) {
        return new Result(be.getCode(), null, be.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex) {
        //记录日志
        //发送消息给运维
        //发送邮件给开发人员
        return new Result(Code.SYSTEM_UNKNOWN_ERR, null, "系统繁忙，请稍后再试！");
    }
}
