package com.example.limit.jointpoint;

import com.alibaba.fastjson.JSON;
import com.example.limit.annotation.WhiteList;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class WhiteListJointPoint {
    private Logger log = LoggerFactory.getLogger(WhiteListJointPoint.class);
    @Resource
    private List<String> whiteListConfig;

    @Pointcut("@annotation(com.example.limit.annotation.WhiteList)")
    public void point() {
    }

    @Around("point()&& @annotation(whiteList)")
    public Object doFilter(ProceedingJoinPoint jp, WhiteList whiteList) throws Throwable {
        String key = whiteList.key();

        if (StringUtils.isEmpty(key)) {
            return jp.proceed();
        }
        log.info("whiteList:" + whiteListConfig);
        String filedValue = getFiledValue(whiteList.key(), jp.getArgs());

        if (whiteListConfig.contains(filedValue)) {
            return jp.proceed();
        }

        Method method = getMethod(jp);
        return defaultObj(whiteList, method);
    }

    private String getFiledValue(String filed, Object[] args) {
        String filedValue = null;
        for (Object arg : args) {
            log.info("arg:{}", arg);
            try {
                if (null == filedValue || "".equals(filedValue)) {
                    filedValue = BeanUtils.getProperty(arg, filed);
                } else {
                    break;
                }
            } catch (Exception e) {
                if (args.length == 1) {
                    return args[0].toString();
                }
            }
        }
        return filedValue;
    }

    private Method getMethod(ProceedingJoinPoint jp) throws NoSuchMethodException {
        Signature signature = jp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return jp.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
    }


    private Object defaultObj(WhiteList whiteList, Method method) throws InstantiationException, IllegalAccessException {
        Class<?> returnType = method.getReturnType();
        if (StringUtils.isEmpty(whiteList.returnJson())) {
            return returnType.newInstance();
        }
        return JSON.parseObject(whiteList.returnJson(), method.getReturnType());
    }
}
