package com.example.monitor.jointpoint;

import com.example.monitor.annotation.TimeCount;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

@Aspect
public class TimeCountPoint {
    private static Logger log = LoggerFactory.getLogger(TimeCountPoint.class);

    @Pointcut("@annotation(com.example.monitor.annotation.TimeCount)")
    public void aopPoint() {
    }

    @Around("aopPoint() && @annotation(doMonitor)")
    public Object doRouter(ProceedingJoinPoint jp, TimeCount doMonitor) throws Throwable {
        long start = System.currentTimeMillis();
        Method method = getMethod(jp);
        try {
            return jp.proceed();
        } finally {
            long end = System.currentTimeMillis();
            log.info("监控索引：{} 监控描述：{}方法名称：{}方法耗时：{}",
                    doMonitor.key(), doMonitor.desc(), method.getName(), (end - start) + "ms");
        }
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) sig;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }

}
