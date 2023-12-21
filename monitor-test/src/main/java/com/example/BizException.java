package com.example;

public class BizException extends RuntimeException {
    //异常错误编码
    private int code ;
    //异常信息
    private String message;

    private BizException(){}

    public BizException(BizExceptionType exceptionTypeEnum) {
        this.code = exceptionTypeEnum.getCode();
        this.message = exceptionTypeEnum.getDesc();
    }

    public BizException(BizExceptionType exceptionTypeEnum, String message) {
        this.code = exceptionTypeEnum.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
