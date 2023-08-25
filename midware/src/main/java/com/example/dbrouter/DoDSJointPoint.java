package com.example.dbrouter;

import com.example.dbrouter.annotation.DS;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DoDSJointPoint {
    private Logger logger = LoggerFactory.getLogger(DoDSJointPoint.class);

    @Pointcut("@annotation(com.example.dbrouter.annotation.DS)")
    public void pointCut() {
    }


    @Around("pointCut()&&@annotation(ds)")
    public Object doFilter(ProceedingJoinPoint jp, DS ds) throws Throwable {
        String dsName = ds.key();
        // 设置到 ThreadLocal
        try {
            DBContextHolder.setDBKey(dsName);
            logger.debug("cur ds name:{}", dsName);
            return jp.proceed();
        } finally {
            DBContextHolder.clearTBKey();
            DBContextHolder.clearDBKey();
        }
    }
}
