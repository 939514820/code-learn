package com.example.jointpoint;

import com.example.annotation.WhiteList;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Aspect
public class WhiteListJointPoint {
    @Resource
    private String whiteListConfig;
    @Pointcut("@annotation(com.example.annotation.WhiteList)")
    public void point() {
    }

    @Around("point()&& @annotation(whiteList)")
    public Object doFilter(ProceedingJoinPoint jp, WhiteList whiteList) throws Throwable {
        String curValue = whiteList.value();
        if(StringUtils.isEmpty(curValue)){
            return jp.proceed();
        }

        String[] list = whiteListConfig.split(",");
        for (String cur : list) {
            if(cur.equals(curValue)){
                return jp.proceed();
            }
        }
        return defaultObj(jp,whiteList);
    }

    private Object defaultObj(ProceedingJoinPoint jp, WhiteList whiteList) {
//        jp.getTarget().getClass()
        return whiteList.returnJson();
    }
}
