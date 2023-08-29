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
    private final Logger logger = LoggerFactory.getLogger(DoDbRouterJointPoint.class);
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
        int dbCount = 0;
        int tbCount = 0;
        String dbPre = "";
        String tbPre = "";
        try {
            Object dbKeyAttr = getAttrValue(key, jp);
            // TODO 获取当前mappper的db和tb
            String dbName="db";
            String tbName="user_";
            if (dbRouterConfig.getDbs().containsKey(dbName)) {
                DBRouterConfig.DBInfo user = dbRouterConfig.getDbs().get(dbName);
                dbCount = user.getCount();
                dbPre = user.getNamePrefix();
            }
            if (dbRouterConfig.getTbs().containsKey(tbName)) {
                DBRouterConfig.DBInfo user = dbRouterConfig.getTbs().get(tbName);
                tbCount = user.getCount();
                tbPre = user.getNamePrefix();
            }
            int size = dbCount * tbCount;
            // 扰动函数
            int idxSize = (size - 1) & (dbKeyAttr.hashCode() ^ (dbKeyAttr.hashCode() >>> 16));
            // 库表索引
            int dbIdx = idxSize / tbCount + 1;
            int tbIdx = idxSize - tbCount * (dbIdx - 1);
//             设置到 ThreadLocal
            DBContextHolder.setDBKey(dbPre + dbIdx);
            DBContextHolder.setTBKey(tbPre + tbIdx);
            return jp.proceed();
        } finally {
            DBContextHolder.clearTBKey();
            DBContextHolder.clearDBKey();
        }


    }

    private Object getAttrValue(String attr, ProceedingJoinPoint jp) {
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
