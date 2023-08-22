package com.example.jointpoint;

import com.alibaba.fastjson.JSON;
import com.example.annotation.Breaker;
import com.example.annotation.WhiteList;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Aspect
public class DoBreaker {
    @Pointcut("@annotation(com.example.annotation.Breaker)")
    public void pointCut() {
    }

    @Around("pointCut()&& @annotation(breaker)")
    public Object doFilter(ProceedingJoinPoint jp, Breaker breaker) {
        // 计时
        long timeout = breaker.timeout();
        // 执行具体的方法
        // 超时熔断
        MethodSignature signature = (MethodSignature) jp.getSignature();
        Method method = signature.getMethod();
        method.invoke();
        return defaultObj(breaker, method.getReturnType());
    }

    private Object defaultObj(Breaker whiteList, Class<?> returnType) throws InstantiationException, IllegalAccessException {
        if (StringUtils.isEmpty(whiteList.returnJson())) {
            return returnType.newInstance();
        }
        return JSON.parseObject(whiteList.returnJson());
    }
}
