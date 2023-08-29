package com.example.limiter;

import com.example.annotation.DoRateLimiter;
import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;

import java.lang.reflect.Method;

/**
 * 单机限流
 */
public class SimpleLimitService implements LimitService {
    @Override
    public Object doLimit(ProceedingJoinPoint jp, DoRateLimiter limiter) throws Throwable {
        // 限流逻辑
        Method method = getMethod(jp);
        int limit = limiter.limit();
        String name = jp.getTarget().getClass().getName();
        String key = name + "." + method.getName();
        if (!Constants.rateLimiterMap.containsKey(key)) {
            Constants.rateLimiterMap.put(key, RateLimiter.create(limit));
        }
        RateLimiter rateLimiter = Constants.rateLimiterMap.get(key);
        if (rateLimiter.tryAcquire()) {
            return jp.proceed();
        }
        return defaultObj(limiter, method);
    }
}
