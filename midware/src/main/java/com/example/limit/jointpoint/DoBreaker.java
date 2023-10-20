package com.example.limit.jointpoint;

import com.alibaba.fastjson.JSON;
import com.example.limit.annotation.Breaker;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

@Aspect
public class DoBreaker {
    @Pointcut("@annotation(com.example.limit.annotation.Breaker)")
    public void pointCut() {
    }

    @Around("pointCut()&& @annotation(breaker)")
    public Object doFilter(ProceedingJoinPoint jp, Breaker breaker) throws Throwable {
        // 计时
        long timeout = breaker.timeout();
        // 执行具体的方法
        // 超时熔断
        // 开关是否打开 未开->计数
        //

        Method method = getMethod(jp);
        long start = System.currentTimeMillis();
        jp.proceed();
        long end = System.currentTimeMillis();
        if ((end - start) / 1000 > timeout) {

        }
        return defaultObj(breaker, method.getReturnType());
    }

    private Object defaultObj(Breaker whiteList, Class<?> returnType) throws InstantiationException, IllegalAccessException {
        if (StringUtils.isEmpty(whiteList.returnJson())) {
            return returnType.newInstance();
        }
        return JSON.parseObject(whiteList.returnJson());
    }

    private Method getMethod(ProceedingJoinPoint jp) throws NoSuchMethodException {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }
}
