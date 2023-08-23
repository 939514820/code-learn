package com.example.jointpoint;


import com.alibaba.fastjson.JSON;
import com.example.annotation.DoRateLimiter;
import com.example.annotation.WhiteList;
import com.example.limiter.Constants;
import com.example.limiter.SimpleLimitService;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Aspect
public class DoRateLimiterPoint {
    private Logger logger = LoggerFactory.getLogger(DoRateLimiterPoint.class);

    @Pointcut("@annotation(com.example.annotation.DoRateLimiter)")
    public void pointCut() {
    }

    @Around("pointCut() &&@annotation(limiter)")
    public Object doFilter(ProceedingJoinPoint jp, DoRateLimiter limiter) throws Throwable {
        if (limiter.limit() == 0) {
            jp.proceed();
        }
        // 此处可以根据配置获取限流的类型
        SimpleLimitService limitService = new SimpleLimitService();
        return limitService.doLimit(jp, limiter);
    }


}
