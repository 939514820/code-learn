package com.example.exception;

import com.example.ApiResponse;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;

//https://prometheus.github.io/client_java/
@Slf4j
@ControllerAdvice
public class WebExceptionHandler {
//    @Autowired
//    MeterRegistry registry;
//    private Counter failCounter;
//
//    @PostConstruct
//    private void init() {
//        failCounter = Counter.builder("customer_requests_fail_count").tags("method", "xxx")
//                .register(registry);
//    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ApiResponse customerException(BizException e) {
//        failCounter.increment();
        return ApiResponse.error(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResponse exception(Exception e) {
        log.error("error", e);
//        failCounter.increment();
        return ApiResponse.error(new BizException(BizExceptionCode.SYSTEM_ERROR));
    }
}

