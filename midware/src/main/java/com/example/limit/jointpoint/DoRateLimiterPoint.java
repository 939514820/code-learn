package com.example.limit.jointpoint;


import com.example.limit.annotation.DoRateLimiter;
import com.example.limit.limiter.SimpleLimitService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Aspect
public class DoRateLimiterPoint {
    private Logger logger = LoggerFactory.getLogger(DoRateLimiterPoint.class);

    @Pointcut("@annotation(com.example.limit.annotation.DoRateLimiter)")
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
