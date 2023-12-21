package com.example;

import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class WebExceptionHandler {

    //处理程序员主动转换的自定义异常
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ApiResponse customerException(BizException e) {

        return ApiResponse.error(e);
    }

    //处理程序员在程序中未能捕获（遗漏的）异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse exception(Exception e) {
        return ApiResponse.error(new BizException(BizExceptionType.OTHER_ERROR));
    }

    //处理异常校验失败
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public ApiResponse handleBindException(MethodArgumentNotValidException ex) {
//        FieldError fieldError = ex.getBindingResult().getFieldError();
//        return ApiResponse.error(new BizException(BizExceptionType.BAD_REQUEST,
//                fieldError.getDefaultMessage()));
//    }
//
//    //处理异常校验失败
//    @ExceptionHandler(BindException.class)
//    @ResponseBody
//    public ApiResponse handleBindException(BindException ex) {
//        FieldError fieldError = ex.getBindingResult().getFieldError();
//        return ApiResponse.error(new BizException(BizExceptionType.BAD_REQUEST,
//                fieldError.getDefaultMessage()));
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseBody
//    public ApiResponse handleIllegalArgumentException(IllegalArgumentException e) {
//        return ApiResponse.error(
//                new BizException(BizExceptionType.BAD_REQUEST, e.getMessage())
//        );
//    }
//
////    @ExceptionHandler(ModelViewException.class)
////    public ModelAndView viewExceptionHandler(HttpServletRequest req, ModelViewException e) {
////        ModelAndView modelAndView = new ModelAndView();
////
////        //将异常信息设置如modelAndView
////        modelAndView.addObject("exception", e);
////        modelAndView.addObject("url", req.getRequestURL());
////        modelAndView.setViewName("error");
////
////        //返回ModelAndView
////        return modelAndView;
////    }
}

