package com.example;

import org.springframework.http.HttpStatus;

public enum BizExceptionType {

    OK(HttpStatus.OK.value(),null),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(),"您输入的数据错误或您没有权限访问资源！"),
    INTERNAL_SERVER_ERROR (HttpStatus.INTERNAL_SERVER_ERROR.value(),"系统出现异常，请您稍后再试或联系管理员！"),
    OTHER_ERROR(999,"系统出现未知异常，请联系管理员！"),
    SYSTEM_ERROR(500,"系统出现未知异常，请联系管理员！");

    BizExceptionType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String desc;//异常类型中文描述

    private int code; //code

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
