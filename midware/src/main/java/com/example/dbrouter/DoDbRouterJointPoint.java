package com.example.dbrouter;

import com.example.dbrouter.annotation.DBRouter;
import com.example.dbrouter.config.DBRouterConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Aspect
@Component("db-router-point")
public class DoDbRouterJointPoint {
    private Logger logger = LoggerFactory.getLogger(DoDbRouterJointPoint.class);
    @Resource
    private DBRouterConfig dbRouterConfig;

    @Pointcut("@annotation(com.example.dbrouter.annotation.DBRouter)")
    public void pointCut() {
    }

    @Around("pointCut()&&@annotation(dbRouter)")
    public Object doFilter(ProceedingJoinPoint jp, DBRouter dbRouter) throws Throwable {
        String key = dbRouter.key();
        // 获取字段值
        // 计算分库，计算分表
        try {
            Object dbKeyAttr = getAttrValue(key, jp);

            int size = dbRouterConfig.getDbCount() * dbRouterConfig.getTbCount();
            // 扰动函数
            int idxSize = (size - 1) & (dbKeyAttr.hashCode() ^ (dbKeyAttr.hashCode() >>> 16));
            // 库表索引
            int dbIdx = idxSize / dbRouterConfig.getTbCount() + 1;
            int tbIdx = idxSize - dbRouterConfig.getTbCount() * (dbIdx - 1);
            // 设置到 ThreadLocal
            DBContextHolder.setDBKey(dbRouterConfig.getCutInfos().get(0).getDb() +
                    dbRouterConfig.getCutInfos().get(0).getDbSeparate() + String.format("%02d", dbIdx));
            DBContextHolder.setTBKey(String.format("%02d", tbIdx));
            return jp.proceed();
        } finally {
            DBContextHolder.clearTBKey();
            DBContextHolder.clearDBKey();
        }


    }

    private Object getAttrValue(String attr, ProceedingJoinPoint jp) {
//        MethodSignature signature = (MethodSignature) jp.getSignature();
//        Method method = jp.getTarget().getClass().getMethod(signature.getName(), signature.getParameterTypes());
//从参数中取指定属性名的值
        Object[] args = jp.getArgs();
        String filedValue = null;
        for (Object arg : args) {
            try {
                if (!StringUtils.isEmpty(filedValue)) {
                    break;
                }
                filedValue = BeanUtils.getProperty(arg, attr);
            } catch (Exception e) {
                logger.error("获取路由属性值失败 attr：{}", attr, e);
            }
        }
        return filedValue;
    }
}
