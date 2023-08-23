package com.example.limiter;

import com.alibaba.fastjson.JSON;
import com.example.annotation.DoRateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public interface LimitService {
    Object doLimit(ProceedingJoinPoint jp, DoRateLimiter limiter) throws Throwable;
    default Method getMethod(ProceedingJoinPoint jp) throws NoSuchMethodException {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

    default Object defaultObj(DoRateLimiter whiteList, Method method) throws InstantiationException, IllegalAccessException {
        Class<?> returnType = method.getReturnType();
        if (StringUtils.isEmpty(whiteList.defaultReturn())) {
            return returnType.newInstance();
        }
        return JSON.parseObject(whiteList.defaultReturn(), method.getReturnType());
    }
}
