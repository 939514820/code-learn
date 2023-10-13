package com.example.limit.limiter;

import com.example.limit.annotation.DoRateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;

public class DistributeLimitService implements LimitService {
    @Override
    public Object doLimit(ProceedingJoinPoint jp, DoRateLimiter limiter) throws Throwable {
        // 获取数据源
        // 计数
        return null;
    }
}
