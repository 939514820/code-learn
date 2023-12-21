package com.example;

import lombok.Data;

@Data
public class ApiResponse {

    private boolean success;  //请求是否处理成功
    private int code; //请求响应状态码（200、400、500）
    private String message;  //请求结果描述信息
    private Object data; //请求结果数据（通常用于查询操作）

    private ApiResponse(){}

    public static ApiResponse error(BizException e) {
        ApiResponse resultBean = new ApiResponse();
        resultBean.setSuccess(false);
        resultBean.setCode(e.getCode());
        resultBean.setMessage(e.getMessage());
        return resultBean;
    }

    public static ApiResponse error(BizExceptionType customExceptionType,
                                    String errorMessage) {
        ApiResponse resultBean = new ApiResponse();
        resultBean.setSuccess(false);
        resultBean.setCode(customExceptionType.getCode());
        resultBean.setMessage(errorMessage);
        return resultBean;
    }

    public static ApiResponse success(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setCode(200);
        apiResponse.setMessage("请求响应成功!");
        return apiResponse;
    }

    public static ApiResponse success(Object obj){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setCode(200);
        apiResponse.setMessage("请求响应成功!");
        apiResponse.setData(obj);
        return apiResponse;
    }

    public static ApiResponse success(Object obj,String message){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccess(true);
        apiResponse.setCode(200);
        apiResponse.setMessage(message);
        apiResponse.setData(obj);
        return apiResponse;
    }
}
